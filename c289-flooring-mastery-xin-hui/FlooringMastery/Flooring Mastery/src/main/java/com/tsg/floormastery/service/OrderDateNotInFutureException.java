package com.tsg.floormastery.service;

public class OrderDateNotInFutureException extends Exception{
    public OrderDateNotInFutureException(String message) {
        super(message);
    }

    public OrderDateNotInFutureException(String message, Throwable cause) {
        super(message, cause);
    }
}
