package com.example.student_managemnet_system.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("Could not find a user with this id " + id);
    }
}
