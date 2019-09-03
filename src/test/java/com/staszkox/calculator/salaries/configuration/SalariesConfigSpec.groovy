package com.staszkox.calculator.salaries.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class SalariesConfigSpec extends Specification {

    @Autowired
    private SalariesConfig config

    def "Test salaries config load correctness"() {

        expect:
            config.calculationCurrency == "PLN"
            config.workingDaysInMonth == 22
            config.countryRates.length == 3
            config.countryRates[0].countryName == "Poland"
            config.countryRates[0].currencyCode == "PLN"
            config.countryRates[0].incomeTaxPercentage == 19
            config.countryRates[0].fixedCost == 1200
    }
}
