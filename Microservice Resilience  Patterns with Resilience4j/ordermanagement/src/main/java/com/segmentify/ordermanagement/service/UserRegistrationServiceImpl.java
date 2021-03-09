package com.segmentify.ordermanagement.service;

import com.segmentify.ordermanagement.dto.SellerDto;
import com.segmentify.ordermanagement.service.resilience4j.UserRegistrationResilience4j;
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
    public String registerSellerCircuitBreaker(SellerDto sellerDto) {
        long start = System.currentTimeMillis();
        String registerSeller = userRegistrationResilience4j.registerSellerCircuitBreaker(sellerDto);
        log.info("add seller call returned in - {}", System.currentTimeMillis() - start);
        return registerSeller;
    }

   @Override
    public String registerSellerRateLimiter(SellerDto sellerDto) {
        return userRegistrationResilience4j.registerSellerRateLimiter(sellerDto);
    }

    @Override
    public String registerSellerRetry(SellerDto sellerDto) {
        return userRegistrationResilience4j.registerSellerRetry(sellerDto);
    }

    @Override
    public List<SellerDto> getSellersList() {
        return userRegistrationResilience4j.getSellersList();
    }

}
