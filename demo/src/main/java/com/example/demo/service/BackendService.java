package com.example.demo.service;


import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.stereotype.Service;

@Service
public class BackendService {

    private static final String CB_CONFIG = "backendA";

    @CircuitBreaker(name = CB_CONFIG)
    @Bulkhead(name = CB_CONFIG)
    @Retry(name = CB_CONFIG)
    public String failure() {
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "This is a remote exception");
    }

    @CircuitBreaker(name = CB_CONFIG)
    @Bulkhead(name = CB_CONFIG)
    @Retry(name = CB_CONFIG)
    public String success() {
        return "Hello World from backend";
    }

    @CircuitBreaker(name = CB_CONFIG)
    @Bulkhead(name = CB_CONFIG)
    public String successException() {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "This is a remote client exception");
    }

    @CircuitBreaker(name = CB_CONFIG, fallbackMethod = "fallback")
    public String failureWithFallback() {
        return failure();
    }

    private String fallback(HttpServerErrorException ex) {
        return "Recovered HttpServerErrorException: " + ex.getMessage();
    }

    private String fallback(Exception ex) {
        return "Recovered: " + ex.toString();
    }

}
