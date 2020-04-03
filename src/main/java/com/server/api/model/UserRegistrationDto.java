package com.server.api.model;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class UserRegistrationDto {

    @NotNull
    private String email;

    @NotNull
    private String userName;

    @NotNull
    private String password;

    @NotNull
    private String confirmPassword;

    public UserRegistrationDto(@NotNull String email, @NotNull String userName, @NotNull String password, @NotNull String confirmPassword) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegistrationDto that = (UserRegistrationDto) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(confirmPassword, that.confirmPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, userName, password, confirmPassword);
    }
}
