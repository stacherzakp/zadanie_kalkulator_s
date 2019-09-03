package com.staszkox.calculator.salaries.domain.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

abstract class OnlineExchangeRateFinder<T> {

    private static final Logger logger = LoggerFactory.getLogger(OnlineExchangeRateFinder.class);

    private final RestTemplate restTemplate;
    private final Class<T> responseType;

    OnlineExchangeRateFinder(Class<T> responseType) {
        this.responseType = responseType;
        this.restTemplate = new RestTemplate();
    }

    Optional<T> getRates(String url) {

        ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, responseType);

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            logger.warn("Exchange rates [{}] call ends with {} code.", url, responseEntity.getStatusCodeValue());
        }

        return Optional.ofNullable(responseEntity.getBody());
    }
}
