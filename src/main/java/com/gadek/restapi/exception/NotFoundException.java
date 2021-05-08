package com.gadek.restapi.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Long id) {
        super("Could not find id " + id);
    }
}
