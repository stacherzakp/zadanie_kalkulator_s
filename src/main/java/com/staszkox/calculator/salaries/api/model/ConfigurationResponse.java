package com.staszkox.calculator.salaries.api.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ConfigurationResponse implements Serializable {

    private String systemCurrency;
    private List<CountryInfo> countries;

    public ConfigurationResponse(String systemCurrency, List<CountryInfo> countries) {
        this.systemCurrency = systemCurrency;
        this.countries = countries;
    }

    public String getSystemCurrency() {
        return systemCurrency;
    }

    public void setSystemCurrency(String systemCurrency) {
        this.systemCurrency = systemCurrency;
    }

    public List<CountryInfo> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryInfo> countries) {
        this.countries = countries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConfigurationResponse)) return false;
        ConfigurationResponse that = (ConfigurationResponse) o;
        return systemCurrency.equals(that.systemCurrency) &&
                countries.equals(that.countries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(systemCurrency, countries);
    }

    @Override
    public String toString() {
        return "ConfigurationResponse{" +
                "systemCurrency='" + systemCurrency + '\'' +
                ", countries=" + countries +
                '}';
    }
}
