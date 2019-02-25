package com.kansal.jwt_validation_lib.connector;

import com.kansal.jwt_validation_lib.model.ValidateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ValidatingServiceConnector {

    public static final String URL = "http://localhost:9002/validate";
    private final RestTemplate restTemplate;

    @Autowired
    public ValidatingServiceConnector(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isValid(String partnerId, String customerId) {
        ValidateRequest validateRequest = ValidateRequest.builder()
                .partnerId(partnerId)
                .customerId(customerId)
                .build();

        try {
            ResponseEntity<Void> responseEntity = restTemplate.postForEntity(URL, validateRequest, Void.class);
            return responseEntity.getStatusCode().is2xxSuccessful();
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                return false;
            }
            throw ex;
        }
    }
}
