package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ZooGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleException(ZooException zooException){

        ZooErrorResponse zooErrorResponse = new ZooErrorResponse(zooException.getHttpStatus().value(),zooException.getMessage(),System.currentTimeMillis());
        log.error(zooException.getMessage());
        return new ResponseEntity<>(zooErrorResponse,zooException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleException(Exception exception){

        ZooErrorResponse zooErrorResponse = new ZooErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage(),System.currentTimeMillis());
        log.error(exception.getMessage());
        return new ResponseEntity<>(zooErrorResponse,HttpStatus.BAD_REQUEST);
    }

}
