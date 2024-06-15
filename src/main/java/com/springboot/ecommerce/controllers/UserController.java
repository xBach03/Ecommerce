package com.springboot.ecommerce.controllers;

import com.springboot.ecommerce.dtos.user.UserDto;
import com.springboot.ecommerce.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> userList = userService.findAll();
        return userList == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/users/{user-email}")
    public ResponseEntity<UserDto> findByEmail(@PathVariable("user-email") String email) {
        UserDto user = userService.findByEmail(email);
        return user == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(user, HttpStatus.OK);
    }


}
