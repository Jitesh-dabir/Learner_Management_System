package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.dto.JwtRequestDto;
import com.bl.learningmanagementsystem.dto.UserDto;
import com.bl.learningmanagementsystem.response.ResponseDto;

import javax.mail.MessagingException;

public interface IUserDetailsService {
    ResponseDto save(UserDto user);

    boolean resetPassword(String password, String token);

    String getResetPasswordToken(String email) throws MessagingException;

    String getAuthenticationToken(JwtRequestDto authenticationRequest) throws Exception;
}
