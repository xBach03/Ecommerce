package com.springboot.ecommerce.controllers;

import com.springboot.ecommerce.dtos.voucher.VoucherDto;
import com.springboot.ecommerce.models.Voucher;
import com.springboot.ecommerce.services.VoucherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoucherController {
    private final VoucherService voucherService;

    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping("/vouchers")
    public ResponseEntity<List<VoucherDto>> findAll() {
        List<VoucherDto> voucherList = voucherService.findAll();
        if(voucherList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(voucherList, HttpStatus.OK);
    }

    @GetMapping("/vouchers/{voucher-id}")
    public ResponseEntity<VoucherDto> findById(@PathVariable("voucher-id") int voucherId) {
        VoucherDto voucherDto = voucherService.findById(voucherId);
        if(voucherDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(voucherDto, HttpStatus.OK);
    }

    @PostMapping("/vouchers")
    public ResponseEntity<VoucherDto> save(@RequestBody Voucher voucher) {
        if(voucher == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(voucherService.save(voucher), HttpStatus.OK);
    }


}