/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maids.LibraryManagementSystem.exception;

import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Adnan
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
   @ExceptionHandler(EntityNotFoundException.class)
   public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException e) {
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
   }
   
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
       Map<String, String> errors = new HashMap<>();
       ex.getBindingResult().getAllErrors().forEach(error -> {
           String fieldName = ((FieldError) error).getField();
           String errorMessage = error.getDefaultMessage();
           errors.put(fieldName, errorMessage);
       });
       return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
   }
   
   @ExceptionHandler(Exception.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public ResponseEntity<String> handleConnversion(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
   }
   
}
