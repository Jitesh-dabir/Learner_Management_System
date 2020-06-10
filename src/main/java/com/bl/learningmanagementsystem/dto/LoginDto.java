package com.bl.learningmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
public class LoginDto implements Serializable {
    @NotEmpty(message = "Please Enter Email")
    private String email;

    @NotEmpty(message = "Please Enter Password")
    private String password;

    @Override
    public String toString() {
        return "LoginDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
