package com.staszkox.calculator.salaries.domain.exchange;

import com.staszkox.calculator.salaries.configuration.ExchangeRatesConfig;
import com.staszkox.calculator.salaries.configuration.OnlineExchangeRatesConfig;
import com.staszkox.calculator.salaries.domain.exception.ExchangeRateNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * For https://api.exchangeratesapi.io/latest converter.
 */
@Component
class ExchangeRatesApiIOFinder extends OnlineExchangeRateFinder<ExchangeRatesApiIOFinder.ExchangeRatesIOApiResponse> implements ExchangeRateFinder {

    private final OnlineExchangeRatesConfig onlineConfig;

    @Autowired
    ExchangeRatesApiIOFinder(ExchangeRatesConfig config) {
        super(ExchangeRatesIOApiResponse.class);
        this.onlineConfig = config.getOnline();
    }

    @Override
    public BigDecimal findExchangeRate(String baseCurrency, String exchangeCurrency) {

        String queryParams = buildQueryParams(baseCurrency, exchangeCurrency);
        String fullUrl = onlineConfig.getBaseUrl() + queryParams;

        ExchangeRatesIOApiResponse rates = getRates(fullUrl)
                .orElseThrow(() -> new ExchangeRateNotFound(baseCurrency, exchangeCurrency));

        return rates.rates.get(exchangeCurrency);
    }

    private String buildQueryParams(String baseCurrency, String exchangeCurrency) {
        return String.format("?base=%s&symbols=%s", baseCurrency, exchangeCurrency);
    }

    public static class ExchangeRatesIOApiResponse {
        private Map<String, BigDecimal> rates;

        public Map<String, BigDecimal> getRates() {
            return rates;
        }

        public void setRates(Map<String, BigDecimal> rates) {
            this.rates = rates;
        }
    }
}
