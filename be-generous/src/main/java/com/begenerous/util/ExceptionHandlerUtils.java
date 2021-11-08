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

}
