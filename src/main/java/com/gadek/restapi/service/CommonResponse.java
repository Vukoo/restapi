package com.gadek.restapi.service;

import com.gadek.restapi.response.ApiResponse;
import org.springframework.http.HttpStatus;

public class CommonResponse {

    public static ApiResponse succResponse(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setMessage(HttpStatus.OK.name());
        return apiResponse;
    }

    public static ApiResponse notFoundResponse(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.NOT_FOUND);
        return apiResponse;
    }
}
