package com.emir.ordermanagement.service;

import com.emir.ordermanagement.dto.SellerDto;

import java.util.List;

public interface UserRegistrationService {

    String registerSeller(SellerDto sellerDto);

    List<SellerDto> getSellersList();

    String registerSellerRateLimiter(SellerDto sellerDto);

    String registerSellerRetry(SellerDto sellerDto);
}
