package com.example.Cryptocurrencywatcher.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String name) {

        super(String.format("%s not found",name));
    }

}
