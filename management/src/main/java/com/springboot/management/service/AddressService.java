package com.springboot.management.service;

import com.springboot.management.dto.AddressDto;
import com.springboot.management.entity.Address;
import com.springboot.management.mapper.AddressMapper;
import com.springboot.management.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressMapper addressMapper;
    public AddressDto saveAddress(AddressDto addressDto){
        Address address=addressMapper.dtoToEntity(addressDto);
        address=addressRepository.save(address);
        return addressMapper.entityToDto(address);
    }
    public List<AddressDto> saveAddresses(List<AddressDto> addressDtos){
        List<Address> addresses=addressMapper.dtoToEntity(addressDtos);
        addresses=addressRepository.saveAll(addresses);
        return addressMapper.entityToDto(addresses);
    }
    public List<AddressDto> getAddresses(){
        List<AddressDto> addressDtos=new ArrayList<>();
        List<Address> addressList=addressRepository.findAllNotDeleted();
        for(Address address: addressList){
            AddressDto addressDto=addressMapper.entityToDto(address);
            addressDtos.add(addressDto);
        }
        return addressDtos;
    }
    public AddressDto getAddressById(AddressDto addressDto){
        int id=addressDto.getAddressId();
        Address address= addressRepository.findByIdNotDeleted(id);
        return addressMapper.entityToDto(address);
    }
    public AddressDto getAddressByCity(AddressDto addressDto){
        String city=addressDto.getCity();
        Address address=addressRepository.findByCity(city);
        return addressMapper.entityToDto(address);
    }
    public AddressDto updateAddress(AddressDto addressDto){
       Address existingAddress=addressRepository.findByIdNotDeleted(addressDto.getAddressId());
       existingAddress.setAddressType(addressDto.getAddressType());
       existingAddress.setCity(addressDto.getCity());
       existingAddress.setCountry(addressDto.getCountry());
        addressRepository.save(existingAddress);
        return addressMapper.entityToDto(existingAddress);
    }
    public AddressDto deleteAddress(AddressDto addressDto){
        if(addressDto!=null&& addressDto.getAddressId()>0){
            int id=addressDto.getAddressId();
            Address address=addressRepository.findByIdNotDeleted(id);
            if(address!=null){
                address.setDeleted(true);
                addressRepository.save(address);
                addressDto.setDeleteStatus("Record Deleted");
            }else{
                addressDto.setDeleteStatus("Record not Found");
            }
        }else {
            addressDto=new AddressDto();
            addressDto.setDeleteStatus("Irrelevant Record");
        }
        return addressDto;

    }


}
