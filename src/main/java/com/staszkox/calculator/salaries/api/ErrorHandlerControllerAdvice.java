package com.staszkox.calculator.salaries.api;

import com.staszkox.calculator.salaries.api.model.Error;
import com.staszkox.calculator.salaries.domain.exception.ExchangeRateNotFoundException;
import com.staszkox.calculator.salaries.domain.exception.TaxNotConfiguredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandlerControllerAdvice.class);

    @ExceptionHandler(TaxNotConfiguredException.class)
    public ResponseEntity<Error> handleTaxNotConfiguredException(TaxNotConfiguredException e) {
        Error error = new Error("Tax rates not configured for " + e.getCurrency());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(ExchangeRateNotFoundException.class)
    public ResponseEntity<Error> handleExchangeRateNotFoundException(ExchangeRateNotFoundException e) {
        Error error = new Error("Exchange rate for pair not found: [" + e.getBaseCurrency() + " - " + e.getExchangeCurrency() + "]");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception e) {
        logger.error("Unexpected exception", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error("Internal error"));
    }
}
