package com.tsg.floormastery.service;

public class CustomNameErrorException extends Exception{
    public CustomNameErrorException(String message) {
        super(message);
    }

    public CustomNameErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
