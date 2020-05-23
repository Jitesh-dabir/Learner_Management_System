package com.bl.learningmanagementsystem.dto;

import com.bl.learningmanagementsystem.exception.UserServiceException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDto {
    private UserServiceException.exceptionType type;
    private String errorMessage;
}
