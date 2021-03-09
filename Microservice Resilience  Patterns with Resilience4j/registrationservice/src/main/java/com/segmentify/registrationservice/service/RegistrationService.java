package com.segmentify.registrationservice.service;

import com.segmentify.registrationservice.dto.SellerDto;

import java.util.List;

public interface RegistrationService {

    String addSeller(SellerDto sellerDto);

    List<SellerDto> getSellersList();
}
