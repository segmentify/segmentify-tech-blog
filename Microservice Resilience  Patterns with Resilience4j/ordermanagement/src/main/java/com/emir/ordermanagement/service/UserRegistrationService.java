package com.emir.ordermanagement.service;

import com.emir.ordermanagement.dto.SellerDto;

import java.util.List;

public interface UserRegistrationService {

    String registerSellerCircuitBreaker(SellerDto sellerDto);

    String registerSellerRateLimiter(SellerDto sellerDto);

    String registerSellerRetry(SellerDto sellerDto);

    List<SellerDto> getSellersList();
}
