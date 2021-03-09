package com.emir.registrationservice.service;

import com.emir.registrationservice.dto.ItemDto;
import com.emir.registrationservice.dto.SellerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;

    @Override
    public String addSeller(SellerDto sellerDto) {
        if (!StringUtils.hasText(sellerDto.getEmail())) {
            log.error("email is empty");
            throw new RuntimeException("Seller mail is not valid");
        }
        sellerDto.setId(getSellersList().size() + 1);
        boolean isSellerAdded = registrationRepository.addSeller(sellerDto);
        String message;
        if (isSellerAdded) {
            message = "Successfully registered. Your id is: " + sellerDto.getId() ;
        } else {
            message = "There was some problem in registering the seller. Please try after some time!!";
        }
        log.info("Add seller message - {}", message);
        return message;
    }

    @Override
    public List<SellerDto> getSellersList() {
        List<SellerDto> sellerList = registrationRepository.getSellerList();
        log.info("fetching seller list. Total sellers - {}", sellerList.size());
        return sellerList;
    }

    private SellerDto getDefaultSellerDto() {
        //default value
        SellerDto sellerDto = new SellerDto();
        sellerDto.setId(123456l);
        sellerDto.setFirstName("Emir");
        sellerDto.setLastName("Gökdemir");
        sellerDto.setEmail("a@segmentify.com");

        ItemDto item = new ItemDto();
        item.setId(3456l);
        item.setName("Apple");
        item.setCategory("Computer");
        item.setPrice(12.5);

        List<ItemDto> items = new ArrayList<>();
        items.add(item);

        sellerDto.setItemsSold(items);
        return sellerDto;
    }
}
