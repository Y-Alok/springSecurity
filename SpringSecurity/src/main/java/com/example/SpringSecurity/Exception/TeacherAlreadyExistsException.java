package com.example.SpringSecurity.Exception;

public class TeacherAlreadyExistsException extends RuntimeException{
    public TeacherAlreadyExistsException(String message){
        super(message);
    }
}
