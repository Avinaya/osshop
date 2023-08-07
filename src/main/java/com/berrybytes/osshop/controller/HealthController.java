package com.berrybytes.osshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class HealthController {

    @GetMapping
    public ResponseEntity<String> checkHealth(){
        return ResponseEntity.status(HttpStatus.OK).body("Working good");
    }
}
