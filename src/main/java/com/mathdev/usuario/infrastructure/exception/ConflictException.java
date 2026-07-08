package com.mathdev.usuario.infrastructure.exception;

public class ConflictException extends RuntimeException{

    public ConflictException(String message){
        super(message);
    }

    public ConflictException(String message, Throwable throwable){
        super(message, throwable);
    }

}
