package com.pagonxt.core.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class GenericService {

    public ResponseEntity<Map<String, String>> getHelloWorld(String param1, String param2) {
        Map<String, String> response = new HashMap<String, String>();

        if(param1==null || param2 == null || param1.isEmpty() || param2.isEmpty())  {
            response.put("message", "Parametros invalidos");
            return ResponseEntity.badRequest().body(response);
        }
        response.put("message", "Hello World!");
        return ResponseEntity.ok(response);
    }
}
