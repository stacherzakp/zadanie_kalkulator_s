package com.staszkox.calculator.salaries.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class NetCalculationResponse implements Serializable {

    private BigDecimal salary;
    private String currency;

    public NetCalculationResponse(BigDecimal salary, String currency) {
        this.salary = salary;
        this.currency = currency;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
