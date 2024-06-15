package com.springboot.ecommerce.services;

import com.springboot.ecommerce.dtos.voucher.VoucherDto;
import com.springboot.ecommerce.models.Voucher;
import com.springboot.ecommerce.repositories.VoucherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class VoucherService {
    private final VoucherRepository voucherRepository;

    public VoucherService(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    public VoucherDto toVoucherDto(Voucher voucher) {
        return new VoucherDto(
                voucher.getName(),
                voucher.getDescription(),
                voucher.getDiscountAmount(),
                voucher.getCreatedDate(),
                voucher.getValidDate()
        );
    }

    public Voucher toVoucher(VoucherDto voucherDto) {
        return new Voucher(
                voucherDto.name(),
                voucherDto.description(),
                voucherDto.discountAmount(),
                voucherDto.createdDate(),
                voucherDto.validDate()
        );
    }

    public List<VoucherDto> findAll() {
        return voucherRepository.findAll()
                .stream()
                .map(this::toVoucherDto)
                .collect(Collectors.toList());
    }

    public VoucherDto findById(Integer id) {
        return voucherRepository.findById(id)
                .map(this::toVoucherDto)
                .orElse(null);
    }

    public VoucherDto save(Voucher voucher) {
        voucher.setCreatedDate(LocalDate.now());
        return toVoucherDto(voucherRepository.save(voucher));
    }
}
