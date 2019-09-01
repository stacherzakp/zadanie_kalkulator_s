package com.staszkox.calculator.salaries.domain.exchange


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ExchangeRatesApiIOFinderSpec extends Specification {

    @Autowired
    ExchangeRatesApiIOFinder exchangeRateFinder

    def "Assert service availability, check PLN to PLN exchange rate"() {

        when:
            def exchangeRate = exchangeRateFinder.findExchangeRate("PLN", "PLN")
        then:
            exchangeRate == BigDecimal.ONE
    }
}
