package com.bl.learningmanagementsystem.controller;

import com.bl.learningmanagementsystem.configuration.ApplicationConfiguration;
import com.bl.learningmanagementsystem.dto.JwtRequestDto;
import com.bl.learningmanagementsystem.dto.LoginDto;
import com.bl.learningmanagementsystem.dto.PasswordResetModelDto;
import com.bl.learningmanagementsystem.dto.UserDto;
import com.bl.learningmanagementsystem.exception.LmsAppServiceException;
import com.bl.learningmanagementsystem.model.User;
import com.bl.learningmanagementsystem.response.JwtResponseDto;
import com.bl.learningmanagementsystem.response.ResponseDto;
import com.bl.learningmanagementsystem.service.IUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    /**
     *
     * @param userDto
     * @return response(Registered user)
     */
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> saveUser(@Valid @RequestBody UserDto userDto) {
        User user = userDetailsService.save(userDto);
        return new ResponseEntity<>(new ResponseDto(user, 200, ApplicationConfiguration.getMessageAccessor().getMessage("101")), HttpStatus.CREATED);
    }

    /**
     *
     * @param authenticationRequest
     * @return response(token)
     * @throws Exception
     */
    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponseDto> createAuthenticationToken(@RequestBody JwtRequestDto authenticationRequest) throws Exception {
        String token = userDetailsService.getAuthenticationToken(authenticationRequest);
        return new ResponseEntity<>(new JwtResponseDto(token, ApplicationConfiguration.getMessageAccessor().getMessage("106")), HttpStatus.CREATED);
    }

    /**
     *
     * @param loginDto
     * @return response(userId)
     */
    @GetMapping("/login")
    public ResponseEntity<ResponseDto> login(@Valid @RequestBody LoginDto loginDto) {
        long userId = userDetailsService.loginUser(loginDto);
        return new ResponseEntity<>(new ResponseDto(userId, 200, ApplicationConfiguration.getMessageAccessor().getMessage("102")), HttpStatus.OK);
    }

    /**
     *
     * @param email
     * @return resetPasswordToken
     * @throws MessagingException
     * @throws LmsAppServiceException
     */
    @GetMapping("/requestresetpassword")
    public ResponseEntity<ResponseDto> requestResetPassword(@Valid @RequestParam(value = "email") String email) throws MessagingException, LmsAppServiceException {
        String resetPasswordToken = userDetailsService.getResetPasswordToken(email);
        return new ResponseEntity<>(new ResponseDto(resetPasswordToken, 200,ApplicationConfiguration.getMessageAccessor().getMessage("103")), HttpStatus.ACCEPTED);
    }

    /**
     *
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