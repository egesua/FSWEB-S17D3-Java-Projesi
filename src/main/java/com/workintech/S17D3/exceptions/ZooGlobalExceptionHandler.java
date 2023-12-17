package com.workintech.S17D3.exceptions;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ZooGlobalExceptionHandler {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ZooGlobalExceptionHandler.class);

    @ExceptionHandler

    public ResponseEntity<ZooErrorResponse> handleException(ZooException zooException) {
        log.error("ZooException occured! Exception details: " + zooException.getMessage());
        ZooErrorResponse zooErrorResponse = new ZooErrorResponse(zooException.getHttpStatus().value(), zooException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(zooErrorResponse, zooException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleException(Exception exception) {
        log.error("ZooException occured! Exception details: " + exception.getMessage());
        ZooErrorResponse zooErrorResponse = new ZooErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(zooErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
