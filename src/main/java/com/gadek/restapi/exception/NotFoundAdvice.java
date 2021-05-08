package com.gadek.restapi.exception;

import com.gadek.restapi.response.ApiResponse;
import com.gadek.restapi.service.CommonResponse;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ApiResponse employeeNotFoundHandler(NotFoundException ex) {
        ApiResponse apiResponse = CommonResponse.notFoundResponse();
        apiResponse.setMessage(ex.getMessage());
        return  apiResponse;
    }
}
