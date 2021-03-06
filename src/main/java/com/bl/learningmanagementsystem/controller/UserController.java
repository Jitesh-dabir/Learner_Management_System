package com.bl.learningmanagementsystem.controller;

import com.bl.learningmanagementsystem.configuration.ApplicationConfiguration;
import com.bl.learningmanagementsystem.dto.LoginDto;
import com.bl.learningmanagementsystem.dto.PasswordResetModelDto;
import com.bl.learningmanagementsystem.dto.UserDto;
import com.bl.learningmanagementsystem.exception.LmsAppServiceException;
import com.bl.learningmanagementsystem.model.User;
import com.bl.learningmanagementsystem.response.ResponseDto;
import com.bl.learningmanagementsystem.service.IUserDetailsService;
import com.bl.learningmanagementsystem.util.JwtTokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserDetailsService userDetailsService;

    /**
     * @param userDto
     * @return response(Registered user)
     */
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> saveUser(@Valid @RequestBody UserDto userDto) {
        User user = userDetailsService.save(userDto);
        return new ResponseEntity<>(new ResponseDto(user, 200, ApplicationConfiguration.getMessageAccessor().getMessage("101")), HttpStatus.CREATED);
    }

    /**
     * @param loginDto
     * @return response(userId)
     */
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDto loginDto) throws Exception {
        Map<Object, Object> objectObjectMap = userDetailsService.loginUser(loginDto);
        return new ResponseEntity<>(new ResponseDto(objectObjectMap, 200, ApplicationConfiguration.getMessageAccessor().getMessage("102")), HttpStatus.OK);
    }

    /** Return token to change password
     * @param email
     * @return resetPasswordToken
     * @throws MessagingException
     * @throws LmsAppServiceException
     */
    @GetMapping("/requestresetpassword")
    public ResponseEntity<ResponseDto> requestResetPassword(@Valid @RequestParam(value = "email") String email) throws MessagingException, LmsAppServiceException, JsonProcessingException {
        String resetPasswordToken = userDetailsService.getResetPasswordToken(email);
        return new ResponseEntity<>(new ResponseDto(resetPasswordToken, 200, ApplicationConfiguration.getMessageAccessor().getMessage("103")), HttpStatus.ACCEPTED);
    }

    /** Change password
     * @param passwordRequestModel
     * @return response(Reseted password)
     */
    @PutMapping("/resetpassword")
    public ResponseEntity<ResponseDto> resetPassword(@Valid @RequestBody PasswordResetModelDto passwordRequestModel) {
        User user = userDetailsService.resetPassword(passwordRequestModel.getPassword(), passwordRequestModel.getToken());
        if (!user.equals(null))
            return new ResponseEntity<>(new ResponseDto(user, 200, ApplicationConfiguration.getMessageAccessor().getMessage("104")), HttpStatus.ACCEPTED);
        return new ResponseEntity<>(new ResponseDto(user, 500, ApplicationConfiguration.getMessageAccessor().getMessage("108")), HttpStatus.ACCEPTED);
    }
}