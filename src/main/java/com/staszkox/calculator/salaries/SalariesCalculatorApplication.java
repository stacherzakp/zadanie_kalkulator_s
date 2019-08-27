package com.staszkox.calculator.salaries;

import com.staszkox.calculator.salaries.configuration.CalculatorConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(CalculatorConfig.class)
public class SalariesCalculatorApplication {

    public static void main(String[] args) {

        SpringApplication.run(SalariesCalculatorApplication.class, args);
    }
}