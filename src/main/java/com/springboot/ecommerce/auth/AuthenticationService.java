package com.springboot.ecommerce.auth;

import com.springboot.ecommerce.config.JwtService;
import com.springboot.ecommerce.dtos.user.UserAuthDto;
import com.springboot.ecommerce.dtos.user.UserRegDto;
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

    public AuthenticationResponse userRegister(UserRegDto userRegDto) {
        User user = new User(
                userRegDto.username(),
                passwordEncoder.encode(userRegDto.password()),
                userRegDto.email(),
                Role.USER
        );
        userService.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }


    public AuthenticationResponse userAuthenticate(UserAuthDto userAuthDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuthDto.email(),
                        userAuthDto.password()
                )
        );
        var user = userService.findByEmailForAuthentication(userAuthDto.email());
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse adminRegister(UserRegDto userRegDto) {
        User user = new User(
                userRegDto.username(),
                passwordEncoder.encode(userRegDto.password()),
                userRegDto.email(),
                Role.ADMIN
        );
        userService.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
    public AuthenticationResponse adminAuthenticate(UserAuthDto userAuthDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuthDto.email(),
                        userAuthDto.password()
                )
        );
        var user = userService.findByEmailForAuthentication(userAuthDto.email());
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
