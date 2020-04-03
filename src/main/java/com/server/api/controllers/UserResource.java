package com.server.api.controllers;

import com.server.api.entity.User;
import com.server.api.model.UserDto;
import com.server.api.model.UserRegistrationDto;
import com.server.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class UserResource {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody final UserRegistrationDto userRegistration) throws URISyntaxException {

        logger.debug("REST request to registering user:  {} with email : {} ", userRegistration.getUserName() , userRegistration.getEmail());

        final User newUser =  userService.createUser(userRegistration);

        logger.debug("Registration successful!");

        return ResponseEntity.created(new URI("/api/users/" + newUser.getUsername()))
                .body(newUser);
    }

    @GetMapping("/users/{login}")
    public ResponseEntity<UserDto> getUser(@PathVariable final String login) {
        logger.debug("REST request to get User : {}", login);
        return ResponseEntity.ok(new UserDto(userService.getUserWithAuthoritiesByLogin(login)));
    }

}
