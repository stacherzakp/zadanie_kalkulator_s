package com.staszkox.calculator.salaries.api;

import com.staszkox.calculator.salaries.api.model.ConfigurationResponse;
import com.staszkox.calculator.salaries.api.model.NetCalculationRequest;
import com.staszkox.calculator.salaries.api.model.NetCalculationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private final CalculationServiceFacade serviceAdapter;

    @Autowired
    CalculatorController(CalculationServiceFacade serviceAdapter) {
        this.serviceAdapter = serviceAdapter;
    }

    @PostMapping("/net")
    public ResponseEntity<NetCalculationResponse> netSalary(NetCalculationRequest request) {
        NetCalculationResponse netSalary = serviceAdapter.calculateNetSalary(request);
        return ResponseEntity.ok(netSalary);
    }

    @GetMapping("/config")
    public ResponseEntity<ConfigurationResponse> configuration() {
        ConfigurationResponse configuration = serviceAdapter.getCalculatorConfiguration();
        return ResponseEntity.ok(configuration);
    }
}
