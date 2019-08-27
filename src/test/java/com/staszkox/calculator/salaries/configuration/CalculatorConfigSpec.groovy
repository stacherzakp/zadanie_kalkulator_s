package com.staszkox.calculator.salaries.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class CalculatorConfigSpec extends Specification {

    @Autowired
    private CalculatorConfig config

    def "Test calculator load correctness"() {

        expect:
            config.workingDaysInMonth == 22
            config.countryRates.length == 3
            config.countryRates[0].countryCode == "PL"
            config.countryRates[0].countryName == "Poland"
            config.countryRates[0].currencyCode == "PLN"
            config.countryRates[0].incomeTaxPercentage == 19
            config.countryRates[0].fixedCost == 1200
    }
}
