package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.model.User;
import com.bl.learningmanagementsystem.repository.UserRepository;
import com.bl.learningmanagementsystem.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.Properties;

@Service
public class ForgotPasswordServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private JavaMailSender sender;

    public boolean resetPassword(String password, String token) {

        String encodedPassword = bcryptEncoder.encode(password);
        if (jwtTokenUtil.isTokenExpired(token)) {
            return false;
        }
        long id = Long.parseLong(jwtTokenUtil.getSubjectFromToken(token));

        User user = entityManager.find(User.class, id);
        user.setPassword(encodedPassword);
        User updatedUser = userRepository.save(user);
        if (updatedUser != null && updatedUser.getPassword().equalsIgnoreCase(encodedPassword))
            return true;
        return false;
    }

    public void sentEmail(User user, String token) throws MessagingException {
        String recipientAddress = user.getEmail();
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(recipientAddress);
        helper.setText("Hii " + user.getFirst_name() + "\n" + " You requested to reset password, if YES then click on link put your new password and NO then ignore \n" +
                "http://localhost:8084/reset_password?json={%22password%22:%22" + null + "%22+,%22token%22:" + token + "}");
        helper.setSubject("Password-Reset-Request");
        sender.send(message);
    }
}