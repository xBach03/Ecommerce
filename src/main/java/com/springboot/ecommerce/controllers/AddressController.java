package com.springboot.ecommerce.controllers;

import com.springboot.ecommerce.dtos.address.AddressDto;
import com.springboot.ecommerce.models.Address;
import com.springboot.ecommerce.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
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
    public ResponseEntity<AddressDto> save(@RequestBody Address address) {
        AddressDto addressDtoRes = addressService.save(address);
        if(addressDtoRes== null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(addressDtoRes, HttpStatus.OK);
    }

    @DeleteMapping("addresses/delete/{address-id}")
    public ResponseEntity<AddressDto> delete(@PathVariable("address-id") Integer id) {
        AddressDto deletedAddress = addressService.delete(id);
        return deletedAddress == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(deletedAddress, HttpStatus.OK);
    }
}
