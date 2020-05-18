package com.bl.learningmanagementsystem.controller;

import com.bl.learningmanagementsystem.dto.UserDTO;
import com.bl.learningmanagementsystem.service.SignUpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SignUpController {

    @Autowired
    private SignUpServiceImpl signUpService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(signUpService.save(user));
    }
}
