package com.example.SpringSecurity.Exception;

public class PersonAlreadyExistsException extends RuntimeException{
    public  PersonAlreadyExistsException(String message){
        super(message);
    }
}
