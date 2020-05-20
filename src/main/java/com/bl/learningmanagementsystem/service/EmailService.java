package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.model.User;

import javax.mail.MessagingException;

public interface EmailService {
    void sentEmail(User user, String token) throws MessagingException;
}
