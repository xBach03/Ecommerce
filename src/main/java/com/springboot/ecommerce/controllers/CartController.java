package com.springboot.ecommerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @GetMapping("/carts")
    public String say() {
        return "carts";
    }

}
