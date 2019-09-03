package com.staszkox.calculator.salaries.domain.exchange;

import java.math.BigDecimal;

public interface ExchangeRateFinder {

    BigDecimal findExchangeRate(String baseCurrency, String exchangeCurrency);
}
