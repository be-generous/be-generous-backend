package com.begenerous.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@NoArgsConstructor
public class ResponseBodyUtil {

    private HashMap<String, String> responseBody = new HashMap<>();

    public ResponseBodyUtil addToResponseBody(String key, String value) {
        responseBody.put(key, value);
        return this;
    }

    public HashMap<String, String> createResponseBody() {
        return responseBody;
    }
}
