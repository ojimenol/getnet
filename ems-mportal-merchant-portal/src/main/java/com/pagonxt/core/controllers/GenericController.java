package com.pagonxt.core.controllers;

import com.pagonxt.core.services.GenericService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/generic")
public class GenericController {

    private final GenericService genericAppService;

    public GenericController(GenericService genericAppService) {
        this.genericAppService = genericAppService;
    }


    @GetMapping()
    public ResponseEntity<Map<String, String>> get(@RequestParam(required = false) String param1, @RequestParam(required = false) String param2) {

        return genericAppService.getHelloWorld(param1, param2);
    }

}
