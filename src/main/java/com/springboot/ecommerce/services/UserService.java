package com.springboot.ecommerce.services;

import com.springboot.ecommerce.dtos.user.UserAuthReqDto;
import com.springboot.ecommerce.models.User;
import com.springboot.ecommerce.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserAuthReqDto toUserAuthReqDto(User user) {
        return new UserAuthReqDto(
                user.getEmail(),
                user.getPassword()
        );
    }

    public UserAuthReqDto save(User user) {
        return toUserAuthReqDto(userRepository.save(user));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
