package com.segmentify.ordermanagement.service;

import com.segmentify.ordermanagement.dto.SellerDto;

import java.util.List;

public interface UserRegistrationService {

    String registerSellerCircuitBreaker(SellerDto sellerDto);

    String registerSellerRateLimiter(SellerDto sellerDto);

    String registerSellerRetry(SellerDto sellerDto);

    List<SellerDto> getSellersList();
}
