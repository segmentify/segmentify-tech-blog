package com.segmentify.registration.service;

import com.segmentify.registration.dto.SellerDto;

import java.util.List;

public interface RegistrationService {

    String addSeller(SellerDto sellerDto);

    List<SellerDto> getSellersList();
}
