package com.staszkox.calculator.salaries.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

@ConfigurationProperties(prefix = "calculator", ignoreInvalidFields = true)
public class CalculatorConfig {

    private final int workingDaysInMonth;
    private final CountryRate[] countryRates;

    public CalculatorConfig(int workingDaysInMonth, CountryRate[] countryRates) {
        this.workingDaysInMonth = workingDaysInMonth;
        this.countryRates = countryRates;
    }

    public int getWorkingDaysInMonth() {
        return workingDaysInMonth;
    }

    public CountryRate[] getCountryRates() {
        return countryRates;
    }

    public List<CountryRate> getCountryRatesAsList() {
        return Arrays.asList(countryRates);
    }

    @Override
    public String toString() {
        return "CalculatorConfig{" +
                "workingDaysInMonth=" + workingDaysInMonth +
                ", countryRates=" + Arrays.toString(countryRates) +
                '}';
    }
}
