package com.staszkox.calculator.salaries.api;

import com.staszkox.calculator.salaries.api.model.ConfigurationResponse;
import com.staszkox.calculator.salaries.api.model.NetCalculationRequest;
import com.staszkox.calculator.salaries.api.model.NetCalculationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private final CalculationServiceFacade calculationServiceFacade;

    @Autowired
    CalculatorController(CalculationServiceFacade calculationServiceFacade) {
        this.calculationServiceFacade = calculationServiceFacade;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/net")
    public ResponseEntity<NetCalculationResponse> netSalary(@Valid @RequestBody NetCalculationRequest request) {
        NetCalculationResponse netSalary = calculationServiceFacade.calculateNetSalary(request);
        return ResponseEntity.ok(netSalary);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/config")
    public ResponseEntity<ConfigurationResponse> configuration() {
        ConfigurationResponse configuration = calculationServiceFacade.getCalculatorConfiguration();
        return ResponseEntity.ok(configuration);
    }
}
