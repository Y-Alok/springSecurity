package com.example.SpringSecurity.Exception;

public class TeacherNotFoundException extends RuntimeException{
    public TeacherNotFoundException(String message){
        super(message);
    }
}
