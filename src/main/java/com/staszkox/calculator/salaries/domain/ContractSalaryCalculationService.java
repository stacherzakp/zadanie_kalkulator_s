package com.staszkox.calculator.salaries.domain;

import com.staszkox.calculator.salaries.configuration.CalculatorConfig;
import com.staszkox.calculator.salaries.domain.model.CalculationBase;
import com.staszkox.calculator.salaries.domain.model.CalculationParameters;
import com.staszkox.calculator.salaries.domain.model.MonthSalary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
class ContractSalaryCalculationService implements SalaryCalculationService {

    private final CalculatorConfig calculatorConfig;

    @Autowired
    ContractSalaryCalculationService(CalculatorConfig calculatorConfig) {
        this.calculatorConfig = calculatorConfig;
    }

    @Override
    public MonthSalary calculateNet(CalculationParameters parameters) {
        return new MonthSalary(BigDecimal.TEN, parameters.getCalculationCurrency(), new CalculationBase(BigDecimal.ONE, BigDecimal.ZERO));
    }
}
