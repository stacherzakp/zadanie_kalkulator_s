package com.staszkox.calculator.salaries.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class NetCalculationRequest implements Serializable {

    private BigDecimal dailyIncome;
    private String currency;

    public NetCalculationRequest() {
    }

    public BigDecimal getDailyIncome() {
        return dailyIncome;
    }

    public void setDailyIncome(BigDecimal dailyIncome) {
        this.dailyIncome = dailyIncome;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
