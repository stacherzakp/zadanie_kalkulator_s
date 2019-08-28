package com.staszkox.calculator.salaries.domain.model;

import java.math.BigDecimal;

public class CalculationParameters {

    private BigDecimal dailyIncome;
    private String dailyIncomeCurrency;
    private String calculationCurrency;

    public CalculationParameters() {
    }

    public CalculationParameters(BigDecimal dailyIncome, String dailyIncomeCurrency, String calculationCurrency) {
        this.dailyIncome = dailyIncome;
        this.dailyIncomeCurrency = dailyIncomeCurrency;
        this.calculationCurrency = calculationCurrency;
    }

    public BigDecimal getDailyIncome() {
        return dailyIncome;
    }

    public void setDailyIncome(BigDecimal dailyIncome) {
        this.dailyIncome = dailyIncome;
    }

    public String getDailyIncomeCurrency() {
        return dailyIncomeCurrency;
    }

    public void setDailyIncomeCurrency(String dailyIncomeCurrency) {
        this.dailyIncomeCurrency = dailyIncomeCurrency;
    }

    public String getCalculationCurrency() {
        return calculationCurrency;
    }

    public void setCalculationCurrency(String calculationCurrency) {
        this.calculationCurrency = calculationCurrency;
    }
}
