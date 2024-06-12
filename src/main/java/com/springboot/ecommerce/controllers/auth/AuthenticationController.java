package com.springboot.ecommerce.controllers.auth;


import com.springboot.ecommerce.dtos.user.UserAuthReqDto;
import com.springboot.ecommerce.dtos.user.UserRegReqDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt-api")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register/user")
    public ResponseEntity<AuthenticationResponse> userRegister(
            @RequestBody UserRegReqDto regRequest
    ) {
        AuthenticationResponse authenticationResponse = authenticationService.userRegister(regRequest);
        if(authenticationResponse == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping("/authenticate/user")
    public ResponseEntity<AuthenticationResponse> userAuthenticate(
            @RequestBody UserAuthReqDto authRequest
            ) {
        AuthenticationResponse authenticationResponse = authenticationService.userAuthenticate(authRequest);
        if(authenticationResponse == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> adminRegister(
            @RequestBody UserRegReqDto regRequest
    ) {
        AuthenticationResponse authenticationResponse = authenticationService.adminRegister(regRequest);
        if(authenticationResponse == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping("/authenticate/admin")
    public ResponseEntity<AuthenticationResponse> adminAuthenticate(
            @RequestBody UserAuthReqDto authRequest
    ) {
        AuthenticationResponse authenticationResponse = authenticationService.adminAuthenticate(authRequest);
        if(authenticationResponse == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }
}
