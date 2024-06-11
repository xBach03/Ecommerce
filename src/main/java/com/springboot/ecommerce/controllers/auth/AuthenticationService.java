package com.springboot.ecommerce.controllers.auth;

import com.springboot.ecommerce.config.JwtService;
import com.springboot.ecommerce.dtos.user.UserAuthReqDto;
import com.springboot.ecommerce.models.Role;
import com.springboot.ecommerce.models.User;
import com.springboot.ecommerce.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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

    public AuthenticationResponse register(UserAuthReqDto userAuthReqDto) {
        User user = new User(
                passwordEncoder.encode(userAuthReqDto.password()),
                userAuthReqDto.email(),
                Role.user
        );
        userService.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }


    public AuthenticationResponse authenticate(UserAuthReqDto userAuthReqDto) {
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
