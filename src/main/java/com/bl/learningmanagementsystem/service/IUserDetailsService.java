package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.dto.JwtRequestDto;
import com.bl.learningmanagementsystem.dto.LoginDto;
import com.bl.learningmanagementsystem.dto.UserDto;
import com.bl.learningmanagementsystem.exception.LmsAppServiceException;
import com.bl.learningmanagementsystem.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.mail.MessagingException;

public interface IUserDetailsService {
    User save(UserDto user);

    User resetPassword(String password, String token);

    String getResetPasswordToken(String email) throws MessagingException, LmsAppServiceException;

    String getAuthenticationToken(JwtRequestDto authenticationRequest) throws Exception;

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    long loginUser(LoginDto loginDto);
}
