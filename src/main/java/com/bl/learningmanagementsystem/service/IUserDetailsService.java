package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.responsedto.Response;
import com.bl.learningmanagementsystem.dto.UserDTO;

public interface UserService {
    Response save(UserDTO user);

    boolean resetPassword(String password, String token);
}
