package com.staszkox.calculator.salaries.domain.exception;

public class TaxNotConfiguredException extends RuntimeException {

    private final String currency;

    public TaxNotConfiguredException(String currency) {
        super();
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }
}
