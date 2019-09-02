package com.staszkox.calculator.salaries.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.staszkox.calculator.salaries.api.model.ConfigurationResponse
import com.staszkox.calculator.salaries.api.model.NetCalculationRequest
import com.staszkox.calculator.salaries.api.model.NetCalculationResponse
import com.staszkox.calculator.salaries.domain.exception.ExchangeRateNotFoundException
import com.staszkox.calculator.salaries.domain.exception.TaxNotConfiguredException
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class CalculatorControllerSpec extends Specification {

    def calculationService = Mock(CalculationServiceFacade)
    def MockMvc mockMvc

    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CalculatorController(calculationService))
                .setControllerAdvice(new ErrorHandlerControllerAdvice()).build()
    }

    def "Call /calculator/config and expect successful response"() {

        when:
            def result = mockMvc.perform(get("/calculator/config")).andReturn()
        then:
            1 * calculationService.getCalculatorConfiguration() >> new ConfigurationResponse("PLN", [])
            result.getResponse().getStatus() == 200
    }

    def "Call /calculator/net and expect successful response"() {

        given:
            def request = new NetCalculationRequest()
            request.currency = "PLN"
            request.dailyIncome = BigDecimal.TEN

            def jsonRequest = new ObjectMapper().writeValueAsString(request)
        when:
            def result = mockMvc.perform(post("/calculator/net")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)).andReturn()
        then:
            1 * calculationService.calculateNetSalary(_) >> new NetCalculationResponse(BigDecimal.TEN, "PLN")
            result.getResponse().getStatus() == 200
    }

    def "Handle ExchangeRateNotFoundException"() {

        when:
            def result = mockMvc.perform(get("/calculator/config")).andReturn()
        then:
            calculationService.getCalculatorConfiguration() >> {throw new ExchangeRateNotFoundException("", "")}
            result.getResponse().getStatus() == 500
    }

    def "Handle TaxNotConfiguredException"() {

        when:
            def result = mockMvc.perform(get("/calculator/config")).andReturn()
        then:
            calculationService.getCalculatorConfiguration() >> {throw new TaxNotConfiguredException("")}
            result.getResponse().getStatus() == 500
    }

    def "Handle any exception"() {

        when:
            def result = mockMvc.perform(get("/calculator/config")).andReturn()
        then:
            calculationService.getCalculatorConfiguration() >> {throw new Exception()}
            result.getResponse().getStatus() == 500
    }
}
