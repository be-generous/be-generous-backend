package com.begenerous.exception;

public class RowNotFoundException extends Exception {

    public RowNotFoundException() {
        super();
    }

    public RowNotFoundException(String message) {
        super(message);
    }

    public RowNotFoundException(String message, Exception e) {
        super(message, e);
    }

}
