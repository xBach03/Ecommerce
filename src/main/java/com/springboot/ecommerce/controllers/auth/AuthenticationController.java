package com.springboot.ecommerce.controllers.auth;


import com.springboot.ecommerce.dtos.user.UserAuthReqDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserAuthReqDto regRequest
    ) {
        AuthenticationResponse authenticationResponse = authenticationService.register(regRequest);
        if(authenticationResponse == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody UserAuthReqDto authRequest
            ) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authRequest);
        if(authenticationResponse == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }
}
