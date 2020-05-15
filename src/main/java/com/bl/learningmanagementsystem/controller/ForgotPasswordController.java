package com.bl.learningmanagementsystem.controller;

import com.bl.learningmanagementsystem.dto.Response;
import com.bl.learningmanagementsystem.dto.PasswordRequestModel;
import com.bl.learningmanagementsystem.dto.PasswordResetModel;
import com.bl.learningmanagementsystem.model.User;
import com.bl.learningmanagementsystem.repository.UserRepository;
import com.bl.learningmanagementsystem.service.ForgotPasswordServiceImpl;
import com.bl.learningmanagementsystem.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.AddressException;

@RestController
public class ForgotPasswordController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ForgotPasswordServiceImpl forgotPasswordService;

    @PostMapping("/request_reset_password")
    public Response requestResetPassword(@RequestBody PasswordRequestModel passwordRequestModel) throws AddressException, MessagingException {
        User user = userRepository.findByEmail(passwordRequestModel.getEmail());
        final String token = jwtTokenUtil.generatePasswordResetToken(String.valueOf(user.getId()));
        forgotPasswordService.sentEmail(user, token);
        return new Response(200, "Email sent successfully");
    }

    @PutMapping("/reset_password")
    public Response resetPassword(@RequestBody PasswordResetModel passwordRequestModel) {
        boolean result = forgotPasswordService.resetPassword(passwordRequestModel.getPassword(), passwordRequestModel.getToken());
        if (result)
            return new Response(200, "Successfully updated");
        return null;
    }
}