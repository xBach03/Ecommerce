package com.springboot.ecommerce.services;

import com.springboot.ecommerce.dtos.user.UserRegReqDto;
import com.springboot.ecommerce.models.User;
import com.springboot.ecommerce.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserRegReqDto toUserAuthReqDto(User user) {
        return new UserRegReqDto(
                user.getUsername(),
                user.getPassword(),
                user.getEmail()
        );
    }

    public UserRegReqDto save(User user) {
        return toUserAuthReqDto(userRepository.save(user));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
