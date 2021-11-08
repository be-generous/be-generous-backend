package com.begenerous.exception;

public class NegativeAmountException extends Exception {
    public NegativeAmountException() {
        super();
    }

    public NegativeAmountException(String message) {
        super(message);
    }
}
