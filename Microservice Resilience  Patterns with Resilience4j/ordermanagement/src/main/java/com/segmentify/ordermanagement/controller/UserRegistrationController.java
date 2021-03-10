package com.segmentify.ordermanagement.controller;

import com.segmentify.ordermanagement.dto.SellerDto;
import com.segmentify.ordermanagement.service.UserRegistrationResilience4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserRegistrationController {

    @Autowired
    private UserRegistrationResilience4j userRegistrationResilience4j;

    @PostMapping("/register/seller-circuit-breaker")
    public String registerAsSellerCircuitBreaker(@RequestBody SellerDto sellerDto) {
        return userRegistrationResilience4j.registerSellerCircuitBreaker(sellerDto);
    }
    @PostMapping("/register/seller-rate-limiter")
    public String registerAsSellerRateLimitter(@RequestBody SellerDto sellerDto) {
        return userRegistrationResilience4j.registerSellerRateLimiter(sellerDto);
    }

    @PostMapping("/register/seller-retry")
    public String registerAsSellerRetry(@RequestBody SellerDto sellerDto) {
        return userRegistrationResilience4j.registerSellerRetry(sellerDto);
    }

    @GetMapping("/seller-list")
    public List<SellerDto> getSellersList() {
        return userRegistrationResilience4j.getSellersList();
    }

}
