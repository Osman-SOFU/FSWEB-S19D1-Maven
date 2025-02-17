package com.workintech.s18d2.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(PlantException plantException) {
        log.error("PlantException oluştu: {}", plantException.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(plantException.getMessage());
        return new ResponseEntity<>(errorResponse, plantException.getHttpStatus());
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleGlobalException(Exception exception) {
        log.error("Bilinmeyen hata oluştu: {}", exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Beklenmeyen bir hata oluştu.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
