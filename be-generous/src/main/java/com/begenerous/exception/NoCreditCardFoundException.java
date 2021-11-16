package com.begenerous.exception;

public class NoCreditCardFoundException extends Exception {
    public NoCreditCardFoundException() {
        super();
    }

    public NoCreditCardFoundException(String message) {
        super(message);
    }
}
