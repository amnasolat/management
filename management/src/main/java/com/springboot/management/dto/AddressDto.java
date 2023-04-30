package com.springboot.management.dto;


import com.springboot.management.entity.Address;
import lombok.Data;

@Data
public class AddressDto {

    private int addressId;

    private String addressType;

    private String city;

    private String country;

   // private StudentDto studentDto;
    private String deleteStatus;

}
