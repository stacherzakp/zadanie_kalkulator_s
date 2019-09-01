package com.staszkox.calculator.salaries.domain.exception;

public class ExchangeRateNotFound extends RuntimeException {

    private final String baseCurrency;
    private final String exchangeCurrency;

    public ExchangeRateNotFound(String baseCurrency, String exchangeCurrency) {
        this.baseCurrency = baseCurrency;
        this.exchangeCurrency = exchangeCurrency;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getExchangeCurrency() {
        return exchangeCurrency;
    }
}
