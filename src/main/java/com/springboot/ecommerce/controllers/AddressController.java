package com.springboot.ecommerce.controllers;

import com.springboot.ecommerce.dtos.address.AddressDto;
import com.springboot.ecommerce.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDto>> findAll() {
        List<AddressDto> addressList = addressService.findAll();
        if(addressList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @GetMapping("/addresses/{address-id}")
    public ResponseEntity<AddressDto> findById(@PathVariable("address-id") Integer addressId) {
        AddressDto addressDto = addressService.findById(addressId);
        if(addressDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }

    @PostMapping("/addresses")
    public ResponseEntity<AddressDto> save(@RequestBody AddressDto addressDto) {
        AddressDto addressDtoRes = addressService.save(addressDto);
        if(addressDtoRes== null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(addressDtoRes, HttpStatus.OK);
    }
}
