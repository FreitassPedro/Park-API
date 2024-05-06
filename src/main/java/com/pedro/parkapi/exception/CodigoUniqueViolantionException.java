package com.pedro.parkapi.exception;

public class CodigoUniqueViolantionException extends RuntimeException{

    public CodigoUniqueViolantionException(String message) {
        super(message);
    }
}
