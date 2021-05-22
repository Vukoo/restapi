package com.gadek.restapi.response;

import org.springframework.http.HttpStatus;

public interface BaseResponse {

    public static ApiResponse succResponse(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(HttpStatus.OK.name());
        apiResponse.setCode(200);
        apiResponse.setMessageDetails("");
        return apiResponse;
    }

    public static ApiResponse notFoundResponse(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(HttpStatus.NOT_FOUND.name());
        apiResponse.setCode(404);
        return apiResponse;
    }
}
