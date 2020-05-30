package com.bl.learningmanagementsystem.response;

public class ResponseDto {

    public Object data;
    public int status;
    public String message;

    public ResponseDto(Object data, int status, String message) {
        this.data = data;
        this.message = message;
        this.status = status;
    }
}
