package com.kansal.validating_service.controller;

import com.kansal.validating_service.model.ValidateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidatingController {

    @PostMapping("/validate")
    public ResponseEntity validate(@RequestBody ValidateRequest validateRequest) {
        System.out.println("validateRequest = " + validateRequest);
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}
