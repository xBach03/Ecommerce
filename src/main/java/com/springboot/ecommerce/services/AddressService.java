package com.springboot.ecommerce.services;

import com.springboot.ecommerce.dtos.address.AddressDto;
import com.springboot.ecommerce.dtos.address.AddressReqDto;

import com.springboot.ecommerce.models.Address;
import com.springboot.ecommerce.repositories.AddressRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    public AddressDto toAddressDto(Address address) {
        return new AddressDto(
                address.getCountry(),
                address.getCity(),
                address.getStreet(),
                address.getCreatedDate()
        );
    }

    public List<AddressDto> findAll(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return addressRepository.findAll(pageable)
                .stream()
                .map(this::toAddressDto)
                .collect(Collectors.toList());
    }

    public AddressDto findById(Integer id) {
        return addressRepository.findById(id)
                .map(this::toAddressDto)
                .orElse(null);
    }

    public List<AddressDto> findAllByUserId(Integer id, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return addressRepository.findAllByUserId(id, pageable).stream()
                .map(this::toAddressDto)
                .collect(Collectors.toList());
    }

    public AddressDto save(Address address) {
        address.setCreatedDate(LocalDate.now());
        return toAddressDto(addressRepository.save(address));
    }

    public AddressDto delete(Integer id) {
        AddressDto deletedAddress = findById(id);
        addressRepository.deleteById(id);
        return deletedAddress;
    }
}
