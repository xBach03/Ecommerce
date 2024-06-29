package com.springboot.ecommerce.controllers;

import com.springboot.ecommerce.dtos.user.UserDto;
import com.springboot.ecommerce.models.User;
import com.springboot.ecommerce.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/id/{user-id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<User> findById(@PathVariable("user-id") Integer id) {
        User user = userService.findById(id);
        return user == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/{page-number}/{page-size}")
    public ResponseEntity<List<UserDto>> findAll(@PathVariable("page-number") Integer pageNumber, @PathVariable("page-size") Integer pageSize) {
        List<UserDto> userList = userService.findAll(pageNumber, pageSize);
        return userList == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/users/email/{user-email}")
    public ResponseEntity<UserDto> findByEmail(@PathVariable("user-email") String email) {
        UserDto user = userService.findByEmail(email);
        return user == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/current")
    public ResponseEntity<User> getCurrent() {
        User user = userService.getCurrentUser();
        return user == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(user, HttpStatus.OK);
    }



}
