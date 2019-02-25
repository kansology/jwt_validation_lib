package com.kansal.jwt_validation_lib.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ValidateRequest {
    private final String partnerId;
    private final String customerId;
}
