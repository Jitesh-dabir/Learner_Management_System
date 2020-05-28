package com.bl.learningmanagementsystem.exception;

import com.bl.learningmanagementsystem.response.ErrorMessageDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class EntityExceptionHandler extends ResponseEntityExceptionHandler {

    //Custom exception in global exception handler
    @ExceptionHandler(LmsAppServiceException.class)
    public final ResponseEntity<ErrorMessageDto> handleAnyException(Exception ex, WebRequest request) {
        ErrorMessageDto errorObj = new ErrorMessageDto(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //MethodArgumentNotValidException
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessageDto errorObj = new ErrorMessageDto(new Date(), "From method argument not valid exception",
                request.getDescription(false));
        return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
