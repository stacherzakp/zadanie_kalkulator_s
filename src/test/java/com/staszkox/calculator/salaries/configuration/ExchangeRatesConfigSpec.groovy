package com.staszkox.calculator.salaries.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ExchangeRatesConfigSpec extends Specification {

    @Autowired
    private ExchangeRatesConfig config

    def "Test exchange rates config load correctness"() {

        expect:
            config.online.baseUrl == "https://api.exchangeratesapi.io/latest"
    }
}
