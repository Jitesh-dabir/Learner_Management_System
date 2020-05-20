package com.bl.learningmanagementsystem.controller;

import com.bl.learningmanagementsystem.dto.JwtRequestDto;
import com.bl.learningmanagementsystem.dto.PasswordResetModelDto;
import com.bl.learningmanagementsystem.dto.UserDto;
import com.bl.learningmanagementsystem.response.JwtResponseDto;
import com.bl.learningmanagementsystem.response.ResponseDto;
import com.bl.learningmanagementsystem.service.IUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponseDto> createAuthenticationToken(@RequestBody JwtRequestDto authenticationRequest) throws Exception {
        String token = userDetailsService.getAuthenticationToken(authenticationRequest);
        return ResponseEntity.ok(new JwtResponseDto(token));
    }

    @GetMapping("/login")
    public String login() {
        return "Login successFull";
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> saveUser(@Valid @RequestBody UserDto user) {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    @GetMapping("/requestresetpassword")
    public ResponseDto requestResetPassword(@Valid @RequestParam(value = "email") String email) throws MessagingException {
        String resetPasswordToken = userDetailsService.getResetPasswordToken(email);
        return new ResponseDto(200, resetPasswordToken);
    }

    @PutMapping("/resetpassword")
    public ResponseDto resetPassword(@Valid @RequestBody PasswordResetModelDto passwordRequestModel) {
        boolean result = userDetailsService.resetPassword(passwordRequestModel.getPassword(), passwordRequestModel.getToken());
        if (result)
            return new ResponseDto(200, "Successfully updated");
        return new ResponseDto(500, "UnSuccessFull");
    }
}