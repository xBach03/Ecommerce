package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.models.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {

}
