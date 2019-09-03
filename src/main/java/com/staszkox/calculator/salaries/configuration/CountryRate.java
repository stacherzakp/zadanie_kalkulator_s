package com.staszkox.calculator.salaries.configuration;

import java.math.BigDecimal;

public class CountryRate {

    private final String countryName;
    private final String currencyCode;
    private final BigDecimal incomeTaxPercentage;
    private final BigDecimal fixedCost;

    public static CountryRate empty() {
        return new CountryRate(null, null, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    public CountryRate(String countryName, String currencyCode, BigDecimal incomeTaxPercentage, BigDecimal fixedCost) {
        this.countryName = countryName;
        this.currencyCode = currencyCode;
        this.incomeTaxPercentage = incomeTaxPercentage;
        this.fixedCost = fixedCost;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public BigDecimal getIncomeTaxPercentage() {
        return incomeTaxPercentage;
    }

    public BigDecimal getFixedCost() {
        return fixedCost;
    }

    @Override
    public String toString() {
        return "CountryRate{" +
                "countryName='" + countryName + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", incomeTaxPercentage=" + incomeTaxPercentage +
                ", fixedCost=" + fixedCost +
                '}';
    }
}
