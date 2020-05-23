package com.bl.learningmanagementsystem.dto;

import javax.validation.constraints.NotEmpty;

public class LoginDto {
    @NotEmpty(message = "Please Enter Email")
    public String email;

    @NotEmpty(message = "Please Enter Password")
    public String password;
}
