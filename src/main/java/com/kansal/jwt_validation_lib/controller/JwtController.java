package com.kansal.jwt_validation_lib.controller;

import com.kansal.jwt_validation_lib.model.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class JwtController {

    @GetMapping("/valid")
    public @ResponseBody
    ValidationResult callIfValid() {
        return ValidationResult.builder().isValid(true).build();
    }
}
