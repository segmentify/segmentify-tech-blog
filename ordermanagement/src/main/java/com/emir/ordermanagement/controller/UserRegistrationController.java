package com.emir.ordermanagement.controller;

import com.emir.ordermanagement.dto.SellerDto;
import com.emir.ordermanagement.service.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    @PostMapping("/register/seller")
    public String registerAsSeller(@RequestBody SellerDto sellerDto) {
        return userRegistrationService.registerSeller(sellerDto);
    }

    @GetMapping("/seller-list")
    public List<SellerDto> getSellersList() {
        return userRegistrationService.getSellersList();
    }

    @PostMapping("/register/seller-rate-limiter")
    public String registerAsSellerRateLimitter(@RequestBody SellerDto sellerDto) {
        return userRegistrationService.registerSellerRateLimiter(sellerDto);
    }

    @PostMapping("/register/seller-retry")
    public String registerAsSellerRetry(@RequestBody SellerDto sellerDto) {
        return userRegistrationService.registerSellerRetry(sellerDto);
    }
}
