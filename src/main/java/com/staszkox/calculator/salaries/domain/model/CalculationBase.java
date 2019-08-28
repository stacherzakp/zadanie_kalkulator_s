package com.staszkox.calculator.salaries.domain.model;

import java.math.BigDecimal;

public class CalculationBase {

    private BigDecimal incomeTaxPercentage;
    private BigDecimal fixedCost;

    public CalculationBase(BigDecimal incomeTaxPercentage, BigDecimal fixedCost) {
        this.incomeTaxPercentage = incomeTaxPercentage;
        this.fixedCost = fixedCost;
    }

    public BigDecimal getIncomeTaxPercentage() {
        return incomeTaxPercentage;
    }

    public void setIncomeTaxPercentage(BigDecimal incomeTaxPercentage) {
        this.incomeTaxPercentage = incomeTaxPercentage;
    }

    public BigDecimal getFixedCost() {
        return fixedCost;
    }

    public void setFixedCost(BigDecimal fixedCost) {
        this.fixedCost = fixedCost;
    }

    @Override
    public String toString() {
        return "CalculationBase{" +
                "incomeTaxPercentage=" + incomeTaxPercentage +
                ", fixedCost=" + fixedCost +
                '}';
    }
}
