package com.springboot.management.dto;


import com.springboot.management.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private int addressId;

    private String addressType;

    private String city;

    private String country;
    private String deleteStatus;

}
