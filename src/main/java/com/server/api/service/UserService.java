package com.server.api.service;

import com.server.api.ObjectMapperUtils;
import com.server.api.entity.Role;
import com.server.api.entity.RoleEnum;
import com.server.api.entity.User;
import com.server.api.exception.ApiError;
import com.server.api.exception.ClientException;
import com.server.api.model.UserRegistrationDto;
import com.server.api.repository.RoleRepository;
import com.server.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final  RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public User createUser(UserRegistrationDto userRegistration) {


        if (userRepository.existsByUsername(userRegistration.getUserName())) {
            throw new ClientException(new ApiError(HttpStatus.BAD_REQUEST, "Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(userRegistration.getEmail())) {
            throw new ClientException(new ApiError(HttpStatus.BAD_REQUEST, "Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(userRegistration.getUserName(),
                userRegistration.getEmail(),
                encoder.encode(userRegistration.getPassword()));

        Set<Role> roles = new HashSet<>();
        Optional<Role> modRole = roleRepository.findByName(RoleEnum.ROLE_USER);

        roles.add(modRole.orElseThrow(() -> new RuntimeException(RoleEnum.ROLE_USER + "doesn't exist in database!")));

        user.setRoles(roles);

        return userRepository.save(user);

    }

    public User getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findByUsername(login)
                .orElseThrow(() ->new ClientException(new ApiError(HttpStatus.NOT_FOUND, "User does not exists!")));
    }
}
