package com.emir.ordermanagement.service.impl;

import com.emir.ordermanagement.dto.SellerDto;
import com.emir.ordermanagement.service.UserRegistrationService;
import com.emir.ordermanagement.service.resilience4j.UserRegistrationResilience4j;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserRegistrationResilience4j userRegistrationResilience4j;

    @Override
    public String registerSeller(SellerDto sellerDto) {
        long start = System.currentTimeMillis();
        String registerSeller = userRegistrationResilience4j.registerSeller(sellerDto);
        log.info("add seller call returned in - {}", System.currentTimeMillis() - start);
        return registerSeller;
    }

    @Override
    public List<SellerDto> getSellersList() {
        return userRegistrationResilience4j.getSellersList();
    }

    @Override
    public String registerSellerRateLimiter(SellerDto sellerDto) {
        return userRegistrationResilience4j.getSellersListRateLimiter(sellerDto);
    }

    @Override
    public String registerSellerRetry(SellerDto sellerDto) {
        return userRegistrationResilience4j.registerSellerRetry(sellerDto);
    }
}
