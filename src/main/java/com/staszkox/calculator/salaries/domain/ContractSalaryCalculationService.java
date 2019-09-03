package com.staszkox.calculator.salaries.domain;

import com.staszkox.calculator.salaries.configuration.CountryRate;
import com.staszkox.calculator.salaries.configuration.SalariesConfig;
import com.staszkox.calculator.salaries.domain.exception.TaxNotConfiguredException;
import com.staszkox.calculator.salaries.domain.exchange.ExchangeRateFinder;
import com.staszkox.calculator.salaries.domain.model.CalculationParameters;
import com.staszkox.calculator.salaries.domain.model.MonthSalary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
class ContractSalaryCalculationService implements SalaryCalculationService {

    private static final Logger logger = LoggerFactory.getLogger(ContractSalaryCalculationService.class);

    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    private final SalariesConfig salariesConfig;
    private final ExchangeRateFinder exchangeRateFinder;

    @Autowired
    ContractSalaryCalculationService(SalariesConfig salariesConfig, ExchangeRateFinder exchangeRateFinder) {
        this.salariesConfig = salariesConfig;
        this.exchangeRateFinder = exchangeRateFinder;
    }

    @Override
    public MonthSalary calculateNet(CalculationParameters params) {

        if (isTaxConfigured(params.getDailyIncomeCurrency())) {
            return calculateMonthSalary(params);
        } else {
            logger.error("Tax not configured for {}", params.getDailyIncomeCurrency());
            throw new TaxNotConfiguredException(params.getDailyIncomeCurrency());
        }
    }

    private MonthSalary calculateMonthSalary(CalculationParameters params) {
        BigDecimal monthlyIncome = calculateMonthlyIncome(params);
        BigDecimal tax = calculateTaxes(monthlyIncome, params);
        BigDecimal netSalary = monthlyIncome.subtract(tax);
        BigDecimal netSalaryInCalculationCurrency = toCalculationCurrency(netSalary, params);

        return new MonthSalary(netSalaryInCalculationCurrency, params.getCalculationCurrency());
    }

    private BigDecimal toCalculationCurrency(BigDecimal netSalary, CalculationParameters params) {

        BigDecimal exchangeRate = BigDecimal.ONE;

        if (isCurrencyExchangeNeeded(params)) {
            exchangeRate = exchangeRateFinder.findExchangeRate(params.getDailyIncomeCurrency(), params.getCalculationCurrency());
        }

        return netSalary.multiply(exchangeRate).setScale(2, RoundingMode.HALF_DOWN);
    }

    private boolean isCurrencyExchangeNeeded(CalculationParameters params) {
        return !params.getCalculationCurrency().equals(params.getDailyIncomeCurrency());
    }

    private BigDecimal calculateTaxes(BigDecimal monthlyIncome, CalculationParameters params) {

        CountryRate countryRate = salariesConfig.getCountryRate(params.getDailyIncomeCurrency())
                .orElseGet(CountryRate::empty);

        BigDecimal incomeTax = monthlyIncome.multiply(countryRate.getIncomeTaxPercentage())
                .divide(ONE_HUNDRED, 2, RoundingMode.HALF_DOWN);

        return incomeTax.add(countryRate.getFixedCost());
    }

    private boolean isTaxConfigured(String currency) {
        return salariesConfig.getCountryRate(currency).isPresent();
    }

    private BigDecimal calculateMonthlyIncome(CalculationParameters params) {
        BigDecimal workingDays = BigDecimal.valueOf(salariesConfig.getWorkingDaysInMonth());
        return params.getDailyIncome().multiply(workingDays);
    }
}
