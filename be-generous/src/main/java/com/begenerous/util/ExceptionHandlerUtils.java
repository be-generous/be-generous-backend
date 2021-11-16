package com.begenerous.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ExceptionHandlerUtils {

    public static ResponseEntity<?> invalidInputException(String message) {
        Map<String, String> error = new HashMap<>();
        error.put("errorMessage", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    public static ResponseEntity<?> negativeAmountException(String message) {
        Map<String, String> error = new HashMap<>();
        error.put("errorMessage", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    public static ResponseEntity<?> rowNotFoundException(String message) {
        Map<String, String> error = new HashMap<>();
        error.put("errorMessage", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    public static ResponseEntity<?> noCreditCardFoundException(String message) {
        Map<String, String> error = new HashMap<>();
        error.put("errorMessage", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    public static ResponseEntity<?> internalException(String message) {
        Map<String, String> error = new HashMap<>();
        error.put("errorMessage", message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    public static ResponseEntity<?> unexpectedException(String message) {
        Map<String, String> error = new HashMap<>();
        error.put("errorMessage", "An unexpected error occurred on server side." + message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
