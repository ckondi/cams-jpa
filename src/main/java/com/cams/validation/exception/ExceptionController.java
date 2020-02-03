package com.cams.validation.exception;


import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<Object> exception(ValidationException e){
        return new ResponseEntity<>(e.getValidationResults(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> exception(IllegalArgumentException e){
        Map<String, String> map = new HashMap<>();
        map.put("message", e.getMessage());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {SQLIntegrityConstraintViolationException.class , ConstraintViolationException.class})
    public ResponseEntity<Object> exception(ConstraintViolationException e){
        Map<String, String> map = new HashMap<>();
//        e instanceof ? (() e) : null;
        if(e.getSQLException() instanceof SQLIntegrityConstraintViolationException){
            map.put("message", e.getSQLException().getMessage());
        }
        else {
            map.put("message", e.getMessage());
        }
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> exception(
            MethodArgumentNotValidException e) {
        Map<String, String> map = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            map.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
