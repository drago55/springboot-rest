package com.server.api.model;

import java.util.Objects;

public class JwtToken {

    private String token;

    public JwtToken(String jwt) {
        this.token = jwt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "JwtToken{" +
                "token='" + token + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtToken jwtToken = (JwtToken) o;
        return Objects.equals(token, jwtToken.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}
