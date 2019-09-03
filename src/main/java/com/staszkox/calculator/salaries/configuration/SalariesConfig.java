package com.staszkox.calculator.salaries.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ConfigurationProperties(prefix = "salaries", ignoreInvalidFields = true)
public class SalariesConfig {

    private final String calculationCurrency;
    private final Integer workingDaysInMonth;
    private final CountryRate[] countryRates;

    public SalariesConfig(String calculationCurrency, Integer workingDaysInMonth, CountryRate[] countryRates) {
        this.calculationCurrency = calculationCurrency;
        this.workingDaysInMonth = workingDaysInMonth;
        this.countryRates = countryRates;
    }

    public String getCalculationCurrency() {
        return calculationCurrency;
    }

    public Integer getWorkingDaysInMonth() {
        return workingDaysInMonth;
    }

    public CountryRate[] getCountryRates() {
        return countryRates;
    }

    public List<CountryRate> getCountryRatesAsList() {
        return countryRates != null ? Arrays.asList(countryRates) : new ArrayList<>();
    }

    public Optional<CountryRate> getCountryRate(String currencyCode) {
        return getCountryRatesAsList().stream()
                .filter(countryRate -> currencyCode.equals(countryRate.getCurrencyCode()))
                .findFirst();
    }

    @Override
    public String toString() {
        return "SalariesConfig{" +
                "calculationCurrency=" + calculationCurrency +
                ", workingDaysInMonth=" + workingDaysInMonth +
                ", countryRates=" + Arrays.toString(countryRates) +
                '}';
    }
}
