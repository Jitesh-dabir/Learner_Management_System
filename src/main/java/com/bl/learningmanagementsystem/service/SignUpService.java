package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.dto.Response;
import com.bl.learningmanagementsystem.dto.UserDTO;

public interface SignUpService {
    Response save(UserDTO user);
}
