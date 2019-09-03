package com.staszkox.calculator.salaries;

import com.staszkox.calculator.salaries.configuration.ExchangeRatesConfig;
import com.staszkox.calculator.salaries.configuration.SalariesConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class SalariesCalculatorApplicationValidator {

    private static final Logger logger = LoggerFactory.getLogger(SalariesCalculatorApplicationValidator.class);

    private final ApplicationContext applicationContext;

    @Autowired
    SalariesCalculatorApplicationValidator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {

        SalariesConfig salariesConfig = applicationContext.getBean(SalariesConfig.class);
        boolean validSalariesConfig = validate(salariesConfig);

        ExchangeRatesConfig exchangeRatesConfig = applicationContext.getBean(ExchangeRatesConfig.class);
        boolean validExchangeRatesConfig = validate(exchangeRatesConfig);

        if (!validSalariesConfig || !validExchangeRatesConfig) {
            logger.error("Salaries calculator configuration not valid, please reconfigure your application.");
            SpringApplication.exit(applicationContext, () -> 0);
        }
    }

    private boolean validate(ExchangeRatesConfig exchangeRatesConfig) {
        return exchangeRatesConfig != null && exchangeRatesConfig.getOnline() != null
                && exchangeRatesConfig.getOnline().getBaseUrl() != null;
    }

    private boolean validate(SalariesConfig salariesConfig) {
        return salariesConfig != null && salariesConfig.getWorkingDaysInMonth() != null
                && salariesConfig.getSystemCurrency() != null
                && salariesConfig.getCountryRates() != null;
    }
}
