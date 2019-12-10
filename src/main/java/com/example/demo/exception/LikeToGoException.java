package com.example.demo.exception;

public class LikeToGoException extends RuntimeException {

    public LikeToGoException(String error){
        super(error);
    }
}
