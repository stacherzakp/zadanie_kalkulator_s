package com.staszkox.calculator.salaries.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ConfigurationProperties(prefix = "salaries", ignoreInvalidFields = true)
public class SalariesConfig {

    private final String systemCurrency;
    private final int workingDaysInMonth;
    private final CountryRate[] countryRates;

    public SalariesConfig(String systemCurrency, int workingDaysInMonth, CountryRate[] countryRates) {
        this.systemCurrency = systemCurrency;
        this.workingDaysInMonth = workingDaysInMonth;
        this.countryRates = countryRates;
    }

    public String getSystemCurrency() {
        return systemCurrency;
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

    public Optional<CountryRate> getCountryRate(String currencyCode) {
        return getCountryRatesAsList().stream()
                .filter(countryRate -> currencyCode.equals(countryRate.getCurrencyCode()))
                .findFirst();
    }

    @Override
    public String toString() {
        return "SalariesConfig{" +
                "systemCurrency=" + systemCurrency +
                ", workingDaysInMonth=" + workingDaysInMonth +
                ", countryRates=" + Arrays.toString(countryRates) +
                '}';
    }
}
