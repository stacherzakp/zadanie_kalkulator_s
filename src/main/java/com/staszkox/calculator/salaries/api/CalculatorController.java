package com.staszkox.calculator.salaries.api;

import com.staszkox.calculator.salaries.api.model.ConfigurationResponse;
import com.staszkox.calculator.salaries.api.model.NetCalculationRequest;
import com.staszkox.calculator.salaries.api.model.NetCalculationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private final CalculationServiceFacadeImpl serviceAdapter;

    @Autowired
    CalculatorController(CalculationServiceFacadeImpl serviceAdapter) {
        this.serviceAdapter = serviceAdapter;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/net")
    public ResponseEntity<NetCalculationResponse> netSalary(@RequestBody NetCalculationRequest request) {
        NetCalculationResponse netSalary = serviceAdapter.calculateNetSalary(request);
        return ResponseEntity.ok(netSalary);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/config")
    public ResponseEntity<ConfigurationResponse> configuration() {
        ConfigurationResponse configuration = serviceAdapter.getCalculatorConfiguration();
        return ResponseEntity.ok(configuration);
    }
}
