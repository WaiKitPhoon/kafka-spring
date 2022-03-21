package com.java.techhub.kafka.demo.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAll(Exception ex, HttpServletRequest request) {
        log.error("Exception occurred -", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    @ExceptionHandle(To handle on specific exception)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<Object> handleInternalServerError(Exception ex, HttpServletRequest request) {
//        log.error("Exception occurred -", ex);
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }
//
//    @ExceptionHandler(To handle on specific exception)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> handleBadRequest(Exception ex, HttpServletRequest request) {
//        log.error("Exception occurred -", ex);
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }
}
