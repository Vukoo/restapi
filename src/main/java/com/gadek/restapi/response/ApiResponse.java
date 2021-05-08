package com.gadek.restapi.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiResponse {
    private HttpStatus status;
    private String message;
}
