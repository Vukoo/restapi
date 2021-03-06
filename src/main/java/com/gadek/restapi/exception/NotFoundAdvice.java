package com.gadek.restapi.exception;

import com.gadek.restapi.response.ApiResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class NotFoundAdvice extends ResponseEntityExceptionHandler {
//    @ResponseBody
//    @ExceptionHandler(NotFoundException.class)
////    @ResponseStatus(HttpStatus.NOT_FOUND)
//    ApiResponse employeeNotFoundHandler(NotFoundException ex) {
//        ApiResponse apiResponse = CommonResponse.notFoundResponse();
//        apiResponse.setMessage(ex.getMessage());
//        return  apiResponse;
//    }
    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    ResponseStatusException notFoundHandler(NotFoundException ex){
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct ID", ex);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Access denied")
    ResponseStatusException accessDeniedException(AccessDeniedException ex){
        return new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied!", ex);
    }
//    @ExceptionHandler(EntityNotFoundException.class)
//    protected ResponseEntity<Object> handleEntityNotFound(
//            EntityNotFoundException ex) {
//        ApiError apiError = new ApiError(NOT_FOUND);
//        apiError.setMessage(ex.getMessage());
//        return buildResponseEntity(apiError);
//    }

//    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
//    protected ResponseEntity<Object> handleConstraintViolation(
//            javax.validation.ConstraintViolationException ex) {
//        ApiError apiError = new ApiError(BAD_REQUEST);
//        apiError.setMessage("Validation error");
//        apiError.addValidationErrors(ex.getConstraintViolations());
//        return buildResponseEntity(apiError);
//    }

//    @ExceptionHandler(EntityNotFoundException.class)
//    protected ResponseEntity<Object> handleEntityNotFound(
//            EntityNotFoundException ex) {
//        ApiError apiError = new ApiError(NOT_FOUND);
//        apiError.setMessage(ex.getMessage());
//        return buildResponseEntity(apiError);
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        String reduce = validationList.stream().reduce("", (value, error) -> value = error + " "+ value);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(reduce);
        apiResponse.setMessage(HttpStatus.BAD_REQUEST.name());
        return new ResponseEntity<>(apiResponse, status);
    }



}
