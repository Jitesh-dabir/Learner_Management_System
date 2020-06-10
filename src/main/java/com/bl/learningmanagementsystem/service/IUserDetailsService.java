package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.dto.LoginDto;
import com.bl.learningmanagementsystem.dto.UserDto;
import com.bl.learningmanagementsystem.exception.LmsAppServiceException;
import com.bl.learningmanagementsystem.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.mail.MessagingException;
import java.util.Map;

public interface IUserDetailsService {
    User save(UserDto user);

    User resetPassword(String password, String token);

    String getResetPasswordToken(String email) throws MessagingException, LmsAppServiceException, JsonProcessingException;

    String getAuthenticationToken(String userName, String password) throws Exception;

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    Map<Object, Object> loginUser(LoginDto loginDto) throws Exception;
}
