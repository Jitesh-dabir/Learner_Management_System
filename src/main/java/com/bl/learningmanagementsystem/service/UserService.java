package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.dto.response.Response;
import com.bl.learningmanagementsystem.dto.request.UserDTO;
import com.bl.learningmanagementsystem.model.User;

import javax.mail.MessagingException;

public interface UserService {
    Response save(UserDTO user);
    boolean resetPassword(String password, String token);
    void sentEmail(User user, String token) throws MessagingException;
}
