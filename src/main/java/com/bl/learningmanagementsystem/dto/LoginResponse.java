package com.bl.learningmanagementsystem.dto;

public class LoginResponse {

    int status;
    String message;

    public LoginResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    //Getter and Setter
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
