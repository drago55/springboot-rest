package com.server.api.model;

import com.server.api.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDto {

    private String userName;

    private String email;

    private List<String> roles;

    public UserDto(User user) {
        this.userName = user.getUsername();
        this.email = user.getEmail();
        this.roles = user.getRoles()
                .stream().map(role -> role.getName().toString())
                .collect(Collectors.toList());
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
