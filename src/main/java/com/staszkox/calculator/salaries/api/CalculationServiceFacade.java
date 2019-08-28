package com.staszkox.calculator.salaries.api;

import com.staszkox.calculator.salaries.api.model.ConfigurationResponse;
import com.staszkox.calculator.salaries.api.model.CountryInfo;
import com.staszkox.calculator.salaries.api.model.NetCalculationRequest;
import com.staszkox.calculator.salaries.api.model.NetCalculationResponse;
import com.staszkox.calculator.salaries.configuration.CalculatorConfig;
import com.staszkox.calculator.salaries.domain.SalaryCalculationService;
import com.staszkox.calculator.salaries.domain.model.CalculationParameters;
import com.staszkox.calculator.salaries.domain.model.MonthSalary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class CalculationServiceFacade {

    private final SalaryCalculationService salaryCalculationService;
    private final CalculatorConfig calculatorConfig;

    @Autowired
    CalculationServiceFacade(SalaryCalculationService salaryCalculationService, CalculatorConfig calculatorConfig) {
        this.salaryCalculationService = salaryCalculationService;
        this.calculatorConfig = calculatorConfig;
    }

    NetCalculationResponse calculateNetSalary(NetCalculationRequest request) {

        CalculationParameters parameters = new CalculationParameters();
        parameters.setCalculationCurrency(calculatorConfig.getSystemCurrency());
        parameters.setDailyIncome(request.getDailyIncome());
        parameters.setDailyIncomeCurrency(request.getCurrency());

        MonthSalary monthSalary = salaryCalculationService.calculateNet(parameters);

        NetCalculationResponse netCalculation = new NetCalculationResponse(monthSalary.getSalary(), monthSalary.getCurrency());
        return netCalculation;
    }

    ConfigurationResponse getCalculatorConfiguration() {

        List<CountryInfo> countries = calculatorConfig.getCountryRatesAsList().stream()
                .map(countryRate -> new CountryInfo(countryRate.getCountryName(), countryRate.getCurrencyCode()))
                .collect(Collectors.toList());
        return new ConfigurationResponse(calculatorConfig.getSystemCurrency(), countries);
    }
}
