package com.staszkox.calculator.salaries.api.model;

import java.io.Serializable;
import java.util.Objects;

public class CountryInfo implements Serializable {

    private String name;
    private String currency;

    public CountryInfo() {
    }

    public CountryInfo(String name, String currency) {
        this.name = name;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CountryInfo)) return false;
        CountryInfo countryInfo = (CountryInfo) o;
        return Objects.equals(name, countryInfo.name) &&
                Objects.equals(currency, countryInfo.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, currency);
    }
}
