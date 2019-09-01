package com.staszkox.calculator.salaries.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "exchange-rates", ignoreInvalidFields = true)
public class ExchangeRatesConfig {

    private final OnlineExchangeRatesConfig online;

    public ExchangeRatesConfig(OnlineExchangeRatesConfig online) {
        this.online = online;
    }

    public OnlineExchangeRatesConfig getOnline() {
        return online;
    }

    @Override
    public String toString() {
        return "ExchangeRatesConfig{" +
                "online=" + online +
                '}';
    }
}
