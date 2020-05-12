package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.dto.LoginResponse;
import com.bl.learningmanagementsystem.model.User;
import com.bl.learningmanagementsystem.dto.UserDTO;

public interface SignUpService {
    public LoginResponse save(UserDTO user);
}
