package com.medistore.MedServer.exception;

public class ItemAlreadyExistException extends RuntimeException {
    public ItemAlreadyExistException(String message){
        super(message);
    }
}
