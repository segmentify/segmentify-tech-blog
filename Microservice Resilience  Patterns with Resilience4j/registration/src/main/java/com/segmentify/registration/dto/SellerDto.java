package com.segmentify.registration.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SellerDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<ItemDto> itemsSold;
}
