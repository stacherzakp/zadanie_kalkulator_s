package com.staszkox.calculator.salaries.domain.model;

import java.math.BigDecimal;

public class MonthSalary {

    private BigDecimal salary;
    private String currency;
    private CalculationBase calculationBase;

    public MonthSalary() {
    }

    public MonthSalary(BigDecimal salary, String currency, CalculationBase calculationBase) {
        this.salary = salary;
        this.currency = currency;
        this.calculationBase = calculationBase;
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

    public CalculationBase getCalculationBase() {
        return calculationBase;
    }

    public void setCalculationBase(CalculationBase calculationBase) {
        this.calculationBase = calculationBase;
    }

    @Override
    public String toString() {
        return "MonthSalary{" +
                "salary=" + salary +
                ", currency='" + currency + '\'' +
                ", calculationBase=" + calculationBase +
                '}';
    }
}
