package com.begenerous.exception;

import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException() {
        super();
    }

    public InvalidInputException(String message) {
        super(message);
    }
}
