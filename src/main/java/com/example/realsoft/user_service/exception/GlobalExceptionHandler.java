package com.example.realsoft.user_service.exception;

import com.example.realsoft.user_service.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({APIException.class, ResourceNotFound.class, Exception.class})
    public ResponseEntity<ErrorDetails> handleException(Exception exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if(exception instanceof APIException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (exception instanceof ResourceNotFound) {
            status =HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(errorDetails, status);
    }
}
