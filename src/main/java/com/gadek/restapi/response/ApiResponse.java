package com.gadek.restapi.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class ApiResponse implements BaseResponse {
    private Integer code;
    private String message;
    private String messageDetails;
    @Value("${rest.api.version}")
    private String apiVersion;
}
