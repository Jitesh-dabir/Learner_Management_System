package com.bl.learningmanagementsystem.response;

public class ResponseDto {

    int status;
    String message;

    public ResponseDto(int status, String message) {
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
