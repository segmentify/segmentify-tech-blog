package com.emir.registrationservice.service;

import com.emir.registrationservice.dto.SellerDto;

import java.util.List;

public interface RegistrationService {

    String addSeller(SellerDto sellerDto);

    List<SellerDto> getSellersList();
}
