package com.tsg.floormastery.service;

public class AreaNumberException extends Exception{
    public AreaNumberException(String message) {
        super(message);
    }

    public AreaNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
