package com.staszkox.calculator.salaries.domain;

import com.staszkox.calculator.salaries.domain.model.CalculationParameters;
import com.staszkox.calculator.salaries.domain.model.MonthSalary;

public interface SalaryCalculationService {

    MonthSalary calculateNet(CalculationParameters parameters);
}
