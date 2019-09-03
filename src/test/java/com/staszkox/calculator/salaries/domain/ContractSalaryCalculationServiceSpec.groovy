package com.staszkox.calculator.salaries.domain


import com.staszkox.calculator.salaries.configuration.CountryRate
import com.staszkox.calculator.salaries.configuration.SalariesConfig
import com.staszkox.calculator.salaries.domain.exception.TaxNotConfiguredException
import com.staszkox.calculator.salaries.domain.exchange.ExchangeRateFinder
import com.staszkox.calculator.salaries.domain.model.CalculationParameters
import spock.lang.Specification
import spock.lang.Unroll

class ContractSalaryCalculationServiceSpec extends Specification {

    SalariesConfig systemConfig
    ExchangeRateFinder exchangeRateFinder
    ContractSalaryCalculationService contractSalaryCalculationService

    void setup() {
        def polandRate = new CountryRate("Poland", "PLN", new BigDecimal(19), new BigDecimal(1200))
        def englandRate = new CountryRate("England", "GBP", new BigDecimal(25), new BigDecimal(1500))
        def noTaxesCountryRate = new CountryRate("Dream", "NoTax", BigDecimal.ZERO, BigDecimal.ZERO)
        CountryRate[] rates = [polandRate, englandRate, noTaxesCountryRate]

        def systemCurrency = "PLN"
        def workingDaysInMonth = 22

        systemConfig = new SalariesConfig(systemCurrency, workingDaysInMonth, rates)
        exchangeRateFinder = Mock(ExchangeRateFinder)
        contractSalaryCalculationService = new ContractSalaryCalculationService(systemConfig, exchangeRateFinder)
    }

    @Unroll
    def "Calculate net salary"() {

        given:
            def calculationParams = new CalculationParameters()
            calculationParams.dailyIncome = dailyIncome
            calculationParams.dailyIncomeCurrency = dailyIncomeCurrency
            calculationParams.calculationCurrency = calculationCurrency
        when:
            def salary = contractSalaryCalculationService.calculateNet(calculationParams)
        then:
            exchangeRateFinder.findExchangeRate("PLN", "GBP") >> new BigDecimal("0.22")
            exchangeRateFinder.findExchangeRate("NoTax", "PLN") >> new BigDecimal("3")
            exchangeRateFinder.findExchangeRate("GBP", "PLN") >> new BigDecimal("4.23")
            salary.salary == expectedSalary
            salary.currency == calculationParams.calculationCurrency
        where:
            dailyIncome         | dailyIncomeCurrency | calculationCurrency | expectedSalary
            new BigDecimal(100) | "NoTax"             | "PLN"               | new BigDecimal("6600")
            new BigDecimal(100) | "GBP"               | "PLN"               | new BigDecimal("634.50")
            new BigDecimal(100) | "PLN"               | "GBP"               | new BigDecimal("128.04")
            new BigDecimal(100) | "PLN"               | "PLN"               | new BigDecimal("582.00")
            new BigDecimal(0)   | "NoTax"             | "NoTax"             | new BigDecimal("0")
            new BigDecimal(0)   | "PLN"               | "PLN"               | new BigDecimal("-1200.00")
    }

    def "Throw exception because incoming currency tax not configured"() {

        given:
            def calculationParams = new CalculationParameters()
            calculationParams.dailyIncome = BigDecimal.ONE
            calculationParams.dailyIncomeCurrency = "NotConfigured"
            calculationParams.calculationCurrency = "NoTax"
        when:
            def salary = contractSalaryCalculationService.calculateNet(calculationParams)
        then:
            thrown(TaxNotConfiguredException)
    }

    def "Do not call exchange rate finder for same currencies calculation"() {

        given:
            def calculationParams = new CalculationParameters()
            calculationParams.dailyIncome = BigDecimal.ONE
            calculationParams.dailyIncomeCurrency = "NoTax"
            calculationParams.calculationCurrency = "NoTax"
        when:
            def salary = contractSalaryCalculationService.calculateNet(calculationParams)
        then:
            0 * exchangeRateFinder.findExchangeRate(_, _)
            salary.salary == new BigDecimal("22.00")
            salary.currency == calculationParams.calculationCurrency
    }

}
