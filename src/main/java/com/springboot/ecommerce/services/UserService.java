package com.springboot.ecommerce.services;

import com.springboot.ecommerce.dtos.user.UserAuthDto;
import com.springboot.ecommerce.dtos.user.UserDto;
import com.springboot.ecommerce.dtos.user.UserRegDto;
import com.springboot.ecommerce.models.User;
import com.springboot.ecommerce.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserRegDto toUserRegReqDto(User user) {
        return new UserRegDto(
                user.getUsername(),
                user.getPassword(),
                user.getEmail()
        );
    }

    public UserAuthDto toUserAuthDto(User user) {
        return new UserAuthDto(
                user.getEmail(),
                user.getPassword()
        );
    }

    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getPassword(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getGender(),
                user.getCreatedDate()
        );
    }

    public UserRegDto save(User user) {

        return toUserRegReqDto(userRepository.save(user));
    }

    public User findByEmailForAuthentication(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(this::toUserDto)
                .collect(Collectors.toList());
    }

    public UserDto findByEmail(String email) {
        return userRepository.findByEmail(email).map(this::toUserDto).orElse(null);
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getCurrentUser() {
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            user = userRepository.findByEmail(email).orElse(null);
        }
        return user;
    }
}
