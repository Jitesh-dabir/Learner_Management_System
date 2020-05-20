package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender sender;

    //Method to send email
    public void sentEmail(User user, String token) throws MessagingException {
        String recipientAddress = user.getEmail();
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(recipientAddress);
        helper.setText("Hii " + user.getFirstName() + "\n" + " You requested to reset password, if YES then click on link put your new password and NO then ignore \n" +
                "http://localhost:8084/reset_password?json={%22password%22:%22" + null + "%22+,%22token%22:" + token + "}");
        helper.setSubject("Password-Reset-Request");
        sender.send(message);
    }
}
