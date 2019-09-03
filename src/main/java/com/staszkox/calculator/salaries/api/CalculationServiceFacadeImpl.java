package com.staszkox.calculator.salaries.api;

import com.staszkox.calculator.salaries.api.model.ConfigurationResponse;
import com.staszkox.calculator.salaries.api.model.CountryInfo;
import com.staszkox.calculator.salaries.api.model.NetCalculationRequest;
import com.staszkox.calculator.salaries.api.model.NetCalculationResponse;
import com.staszkox.calculator.salaries.configuration.SalariesConfig;
import com.staszkox.calculator.salaries.domain.SalaryCalculationService;
import com.staszkox.calculator.salaries.domain.model.CalculationParameters;
import com.staszkox.calculator.salaries.domain.model.MonthSalary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class CalculationServiceFacadeImpl implements CalculationServiceFacade {

    private final SalaryCalculationService salaryCalculationService;
    private final SalariesConfig salariesConfig;

    @Autowired
    CalculationServiceFacadeImpl(SalaryCalculationService salaryCalculationService, SalariesConfig salariesConfig) {
        this.salaryCalculationService = salaryCalculationService;
        this.salariesConfig = salariesConfig;
    }

    @Override
    public NetCalculationResponse calculateNetSalary(NetCalculationRequest request) {
        CalculationParameters parameters = toCalculationParams(request);
        MonthSalary monthSalary = salaryCalculationService.calculateNet(parameters);
        return toCalculationResponse(monthSalary);
    }

    @Override
    public ConfigurationResponse getCalculatorConfiguration() {
        return buildConfigurationResponse();
    }

    private NetCalculationResponse toCalculationResponse(MonthSalary monthSalary) {
        return new NetCalculationResponse(monthSalary.getSalary(), monthSalary.getCurrency());
    }

    private CalculationParameters toCalculationParams(NetCalculationRequest request) {
        CalculationParameters parameters = new CalculationParameters();
        parameters.setCalculationCurrency(salariesConfig.getSystemCurrency());
        parameters.setDailyIncome(request.getDailyIncome());
        parameters.setDailyIncomeCurrency(request.getCurrency());
        return parameters;
    }

    private ConfigurationResponse buildConfigurationResponse() {
        List<CountryInfo> countries = salariesConfig.getCountryRatesAsList().stream()
                .map(countryRate -> new CountryInfo(countryRate.getCountryName(), countryRate.getCurrencyCode()))
                .collect(Collectors.toList());

        return new ConfigurationResponse(salariesConfig.getSystemCurrency(), countries);
    }
}
