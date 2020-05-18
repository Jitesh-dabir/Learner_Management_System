package com.bl.learningmanagementsystem.dto.request;

import javax.validation.constraints.Email;

public class PasswordRequestModel {

    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}