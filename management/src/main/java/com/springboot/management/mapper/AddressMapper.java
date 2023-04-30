package com.springboot.management.mapper;

import com.springboot.management.dto.AddressDto;
import com.springboot.management.entity.Address;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressMapper {

    public Address dtoToEntity(AddressDto addressDto) {
        Address address = new Address();
        address.setAddressId(addressDto.getAddressId());
        address.setAddressType(addressDto.getAddressType());
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        return address;
    }
    public AddressDto entityToDto(Address address){
        AddressDto addressDto=new AddressDto();
        addressDto.setAddressId(address.getAddressId());
        addressDto.setAddressType(address.getAddressType());
        addressDto.setCity(address.getCity());
        addressDto.setCountry(address.getCountry());
        return addressDto;
    }

    public List<AddressDto> entityToDto(List<Address> addresses){
        return addresses.stream().map(x -> entityToDto(x)).collect(Collectors.toList());

    }
    public List<Address> dtoToEntity(List<AddressDto> addressDtos){
        return addressDtos.stream().map(x-> dtoToEntity(x)).collect(Collectors.toList());
    }
}
