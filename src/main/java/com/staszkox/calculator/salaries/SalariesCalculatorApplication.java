package com.staszkox.calculator.salaries;

import com.staszkox.calculator.salaries.configuration.SalariesConfig;
import com.staszkox.calculator.salaries.configuration.ExchangeRatesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({SalariesConfig.class, ExchangeRatesConfig.class})
public class SalariesCalculatorApplication {

    public static void main(String[] args) {

        SpringApplication.run(SalariesCalculatorApplication.class, args);
    }
}