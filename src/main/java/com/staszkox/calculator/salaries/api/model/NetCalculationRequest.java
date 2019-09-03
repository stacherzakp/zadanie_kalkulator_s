package com.staszkox.calculator.salaries.api.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class NetCalculationRequest implements Serializable {

    @NotNull(message = "Daily income cannot be empty.")
    private BigDecimal dailyIncome;

    @NotEmpty(message = "Income currency cannot be empty.")
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
