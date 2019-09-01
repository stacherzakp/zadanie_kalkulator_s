package com.staszkox.calculator.salaries.api

import com.staszkox.calculator.salaries.api.model.CountryInfo
import com.staszkox.calculator.salaries.api.model.NetCalculationRequest
import com.staszkox.calculator.salaries.configuration.CountryRate
import com.staszkox.calculator.salaries.configuration.SalariesConfig
import com.staszkox.calculator.salaries.domain.SalaryCalculationService
import com.staszkox.calculator.salaries.domain.model.MonthSalary
import spock.lang.Specification

class CalculationServiceFacadeImplSpec extends Specification {

    SalariesConfig systemConfig
    SalaryCalculationService salaryCalculationService
    CalculationServiceFacadeImpl facade

    void setup() {
        salaryCalculationService = Mock(SalaryCalculationService)

        def polandRate = new CountryRate("Poland", "PLN", new BigDecimal(19), new BigDecimal(1200))
        def englandRate = new CountryRate("England", "GBP", new BigDecimal(25), new BigDecimal(1500))
        CountryRate[] rates = [polandRate, englandRate]

        def systemCurrency = "PLN"
        def workingDaysInMonth = 22

        systemConfig = new SalariesConfig(systemCurrency, workingDaysInMonth, rates)
        facade = new CalculationServiceFacadeImpl(salaryCalculationService, systemConfig)
    }

    def "Run net salary calculation successfully"() {

        given:
            def request = new NetCalculationRequest()
            request.dailyIncome = BigDecimal.ONE
            request.currency = "PLN"

            def mockedCalculatedSalary = new MonthSalary()
            mockedCalculatedSalary.salary = BigDecimal.TEN
            mockedCalculatedSalary.currency = "GBP"
        when:
            def calculatedSalary = facade.calculateNetSalary(request)
        then:
            1 * salaryCalculationService.calculateNet(_) >> mockedCalculatedSalary
            calculatedSalary.salary == mockedCalculatedSalary.salary
            calculatedSalary.currency == mockedCalculatedSalary.currency
    }

    def "Run get calculator configuration successfully"() {

        when:
            def calculatorConfiguration = facade.getCalculatorConfiguration()
        then:
            calculatorConfiguration.systemCurrency == systemConfig.systemCurrency
            calculatorConfiguration.countries.size() == systemConfig.countryRates.length
            calculatorConfiguration.countries == [
                    new CountryInfo(systemConfig.countryRates[0].countryName, systemConfig.countryRates[0].currencyCode),
                    new CountryInfo(systemConfig.countryRates[1].countryName, systemConfig.countryRates[1].currencyCode)
            ]
    }
}
