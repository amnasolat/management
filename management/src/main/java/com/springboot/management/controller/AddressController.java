package com.springboot.management.controller;

import com.springboot.management.dto.AddressDto;
import com.springboot.management.entity.Address;
import com.springboot.management.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {
     @Autowired
    private AddressService service;
    @PostMapping("/addAddress")
    public AddressDto addAddress(@RequestBody AddressDto addressDto){
        return service.saveAddress(addressDto);
    }
    @PostMapping("/addAddresses")
    public List<AddressDto> addAddresses(@RequestBody List<AddressDto> addressDtos){
        return service.saveAddresses(addressDtos);
    }
    @GetMapping("/addresses")
    public List<AddressDto> findAllAddresses(){
        return service.getAddresses();
    }
    @PostMapping("/address")
    public AddressDto findAddressById(AddressDto addressDto){
        return service.getAddressById(addressDto);
    }
    @PostMapping("/city")
    public AddressDto findByName(AddressDto addressDto){
        return service.getAddressByCity(addressDto);
    }
    @PutMapping("/updateAddress")
    public AddressDto updateAddress(@RequestBody AddressDto addressDto){
        return service.updateAddress(addressDto);
    }
    @PostMapping("/deleteAddress")
    public AddressDto deleteAddress(AddressDto addressDto){
        return service.deleteAddress(addressDto);
    }
}
