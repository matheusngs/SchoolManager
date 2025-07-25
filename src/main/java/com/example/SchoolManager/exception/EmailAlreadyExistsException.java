package com.example.SchoolManager.exception;

public class EmailAlreadyExistsException extends  RuntimeException{
    public EmailAlreadyExistsException(String message){
        super(message);
    }
}
