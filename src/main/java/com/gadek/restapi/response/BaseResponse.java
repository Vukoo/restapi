package com.gadek.restapi.response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

public interface BaseResponse {
    public static ApiResponse succResponse(){
        ApiResponse apiResponse = new ApiResponse();
        succResponse(apiResponse);
        return apiResponse;
    }
    public static ApiResponse succResponse(ApiResponse apiResponse){
        apiResponse.setMessage(HttpStatus.OK.name());
        apiResponse.setCode(200);
        apiResponse.setMessage("");
        return apiResponse;
    }

    public static ApiResponse notFoundResponse(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(HttpStatus.NOT_FOUND.name());
        apiResponse.setCode(404);
        return apiResponse;
    }
}
