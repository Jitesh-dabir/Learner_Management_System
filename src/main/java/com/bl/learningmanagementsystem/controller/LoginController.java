package com.bl.learningmanagementsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping({"/login"})
    public String first() {
        return "Login successFull";
    }
}
