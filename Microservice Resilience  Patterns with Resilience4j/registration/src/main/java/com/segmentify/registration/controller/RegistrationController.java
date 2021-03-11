package com.segmentify.registration.controller;

import com.segmentify.registration.dto.SellerDto;
import com.segmentify.registration.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/add-seller")
    public String addSeller(@RequestBody SellerDto sellerDto) {
        return registrationService.addSeller(sellerDto);
    }

    @GetMapping("/seller-list")
    public List<SellerDto> getSellersList() {
        return registrationService.getSellersList();
    }
}
