package com.staszkox.calculator.salaries.api;

import com.staszkox.calculator.salaries.api.model.ConfigurationResponse;
import com.staszkox.calculator.salaries.api.model.NetCalculationRequest;
import com.staszkox.calculator.salaries.api.model.NetCalculationResponse;

interface CalculationServiceFacade {

    NetCalculationResponse calculateNetSalary(NetCalculationRequest request);

    ConfigurationResponse getCalculatorConfiguration();
}
