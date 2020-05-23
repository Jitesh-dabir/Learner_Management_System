package com.bl.learningmanagementsystem.response;

public class JwtResponseDto {

    public String jwttoken;
    public String message;

    public JwtResponseDto(String token, String message) {
        this.jwttoken = token;
        this.message = message;
    }
}
