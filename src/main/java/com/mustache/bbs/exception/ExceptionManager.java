package com.mustache.bbs.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserNotFoundException e){
        return ResponseEntity.status(e.getHttpStatus())
                .body(e.getMessage() + ErrorCode.NOT_FOUND_USER.getMessage());
    }
}
