package com.server.api.controllers;


import com.server.api.model.JwtToken;
import com.server.api.model.UserLoginDto;
import com.server.api.security.AuthTokenFilter;
import com.server.api.security.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public AuthController(final UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<JwtToken> authenticate(@Valid@RequestBody final UserLoginDto userLogin){
        logger.info("Authenticating user : {} " , userLogin.getUserName());
        final HttpHeaders httpHeaders = new HttpHeaders();
        final JwtToken tokenResponse = userDetailsService.authenticate(userLogin);
        httpHeaders.add(AuthTokenFilter.AUTHORIZATION_HEADER, "Bearer " + tokenResponse.getToken());
        return new ResponseEntity<>(tokenResponse, httpHeaders, HttpStatus.OK);
    }

}
