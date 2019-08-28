package com.staszkox.calculator.salaries.domain

import com.staszkox.calculator.salaries.configuration.CalculatorConfig
import com.staszkox.calculator.salaries.configuration.CountryRate
import com.staszkox.calculator.salaries.domain.model.CalculationParameters
import spock.lang.Specification

class ContractSalaryCalculationServiceSpec extends Specification {

    CalculatorConfig systemConfig
    ContractSalaryCalculationService contractSalaryCalculationService

    void setup() {
        def polandRate = new CountryRate("PL", "Poland", "PLN", new BigDecimal(19), new BigDecimal(1200))
        def englandRate = new CountryRate("UK", "England", "GBP", new BigDecimal(25), new BigDecimal(1500))
        CountryRate[] rates = [polandRate, englandRate]

        def systemCurrency = "PLN"
        def workingDaysInMonth = 22

        systemConfig = new CalculatorConfig(systemCurrency, workingDaysInMonth, rates)
        contractSalaryCalculationService = new ContractSalaryCalculationService(systemConfig)
    }

    def "Run calculate net successfully"() {

        given:
            def calculationParams = new CalculationParameters()
            calculationParams.dailyIncome = BigDecimal.ONE
            calculationParams.dailyIncomeCurrency = "PLN"
            calculationParams.calculationCurrency = "PLN"
        when:
            def salary = contractSalaryCalculationService.calculateNet(calculationParams)
        then:
            salary.salary == BigDecimal.TEN
            salary.currency == calculationParams.calculationCurrency
            salary.calculationBase.fixedCost == BigDecimal.ZERO
            salary.calculationBase.incomeTaxPercentage == BigDecimal.ONE
    }
}
