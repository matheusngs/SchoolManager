package com.example.SchoolManager.exception;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistis(EmailAlreadyExistsException ex){
        Map<String,Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("Mensagem", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(
                HttpStatus.BAD_REQUEST).body(body.toString());

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResouceNotFoundException(ResourceNotFoundException ex){
        Map<String,Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("Mensagem", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(
                HttpStatus.BAD_REQUEST).body(body.toString());
    }
}
