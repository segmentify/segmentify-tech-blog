package com.emir.registrationservice.controller;

import com.emir.registrationservice.dto.SellerDto;
import com.emir.registrationservice.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/addSeller")
    public String addSeller(@RequestBody SellerDto sellerDto) {
        return registrationService.addSeller(sellerDto);
    }

    @GetMapping("/sellerList")
    public List<SellerDto> getSellersList() {
        return registrationService.getSellersList();
    }
}
