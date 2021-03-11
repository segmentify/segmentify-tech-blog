package com.segmentify.ordermanagement.service;

import com.segmentify.ordermanagement.dto.SellerDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserRegistrationResilience4j {

    private final RestTemplate restTemplate;

    @CircuitBreaker(name = "myCircuitBreakerService1", fallbackMethod = "circuitBreakerFallback")
    public String registerSellerCircuitBreaker(SellerDto sellerDto) {
        log.info("calling registerSeller()");
        return restTemplate.postForObject("/registration/add-seller", sellerDto, String.class);
    }

    public String circuitBreakerFallback(SellerDto sellerDto, Throwable t) {
        log.error("Inside circuit breaker fallbackForRegisterSellerCircuitBreaker, cause - {}", t.toString());
        // SEND KAFKA, RABBITMQ or Persist anywhere
        return "Inside circuit breaker fallback method. Some error occurred while calling service for seller registration";
    }

    // RateLimiter
    @RateLimiter(name = "myRateLimiter", fallbackMethod = "rateLimiterFallback")
    public String registerSellerRateLimiter(SellerDto sellerDto) {
        log.info("calling registerSellerRateLimiter()");
        return restTemplate.postForObject("/registration/add-seller", sellerDto, String.class);
    }

    public String rateLimiterFallback(SellerDto sellerDto, Throwable t) {
        log.error("Inside rateLimiterfallback, cause - {}", t.toString());
        return "Inside rateLimiterfallback method. Some error occurred while calling service for seller registration";
    }

    // Retry
    @Retry(name = "myRetryService", fallbackMethod = "retryFallback")
    public String registerSellerRetry(SellerDto sellerDto) {
        log.info("calling registerSellerRetry()");
        return restTemplate.postForObject("/registration/add-seller", sellerDto, String.class);
    }

    public String retryFallback(SellerDto sellerDto, Throwable t) {
        log.error("Inside retryfallback, cause - {}", t.toString());
        return "Inside retryfallback method. Some error occurred while calling service for seller registration";
    }

    @CircuitBreaker(name = "myCircuitBreakerService2", fallbackMethod = "fallbackForGetSeller")
    public List<SellerDto> getSellersList() {
        log.info("calling getSellerList()");
        return restTemplate.getForObject("/registration/seller-list", List.class);
    }

    public List<SellerDto> fallbackForGetSeller(Throwable t) {
        log.error("Inside fallbackForGetSeller, cause - {}", t.toString());
        SellerDto sd = new SellerDto();
        sd.setFirstName("emir");
        sd.setId(1111);
        sd.setEmail("default");
        List<SellerDto> defaultList = new ArrayList<>();
        defaultList.add(sd);
        return defaultList;
    }
}
