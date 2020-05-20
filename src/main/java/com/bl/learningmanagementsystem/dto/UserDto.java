package com.bl.learningmanagementsystem.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {

    @NotNull
    private long id;
    @Size(max = 100)
    @Pattern(regexp ="^[A-Z]+[A-Za-z0-9]{1,}$")
    private String firstName;
    @Size(max = 100)
    @Pattern(regexp ="^[A-Z]+[A-Za-z0-9]{1,}$")
    private String lastName;
    @Email
    private String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]*.{8,}$")
    private String password;
    private long contactNumber;
    private String verified;
    @NotNull
    private LocalDateTime creatorStamp;
    private String creatorUser;
}