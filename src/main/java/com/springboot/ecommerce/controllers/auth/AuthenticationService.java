package com.springboot.ecommerce.controllers.auth;

import com.springboot.ecommerce.config.JwtService;
import com.springboot.ecommerce.dtos.user.UserAuthReqDto;
import com.springboot.ecommerce.dtos.user.UserRegReqDto;
import com.springboot.ecommerce.models.Role;
import com.springboot.ecommerce.models.User;
import com.springboot.ecommerce.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserService userService, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse userRegister(UserRegReqDto userRegReqDto) {
        User user = new User(
                userRegReqDto.username(),
                passwordEncoder.encode(userRegReqDto.password()),
                userRegReqDto.email(),
                Role.USER,
                LocalDate.now()
        );
        userService.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }


    public AuthenticationResponse userAuthenticate(UserAuthReqDto userAuthReqDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuthReqDto.email(),
                        userAuthReqDto.password()
                )
        );
        var user = userService.findByEmail(userAuthReqDto.email());
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse adminRegister(UserRegReqDto userRegReqDto) {
        User user = new User(
                userRegReqDto.username(),
                passwordEncoder.encode(userRegReqDto.password()),
                userRegReqDto.email(),
                Role.ADMIN,
                LocalDate.now()
        );
        userService.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
    public AuthenticationResponse adminAuthenticate(UserAuthReqDto userAuthReqDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuthReqDto.email(),
                        userAuthReqDto.password()
                )
        );
        var user = userService.findByEmail(userAuthReqDto.email());
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
