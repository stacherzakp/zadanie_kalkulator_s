package com.staszkox.calculator.salaries.domain.model;

import java.math.BigDecimal;

public class MonthSalary {

    private BigDecimal salary;
    private String currency;

    public MonthSalary() {
    }

    public MonthSalary(BigDecimal salary, String currency) {
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

    @Override
    public String toString() {
        return "MonthSalary{" +
                "salary=" + salary +
                ", currency='" + currency + '\'' +
                '}';
    }
}
