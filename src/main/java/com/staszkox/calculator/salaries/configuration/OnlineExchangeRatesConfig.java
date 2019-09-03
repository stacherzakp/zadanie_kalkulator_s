package com.staszkox.calculator.salaries.configuration;

public class OnlineExchangeRatesConfig {

    private final String baseUrl;

    public OnlineExchangeRatesConfig(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    @Override
    public String toString() {
        return "OnlineExchangeRatesConfig{" +
                "baseUrl='" + baseUrl + '\'' +
                '}';
    }
}
