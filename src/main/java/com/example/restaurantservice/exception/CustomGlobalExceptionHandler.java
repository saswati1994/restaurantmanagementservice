package com.example.restaurantservice.exception;


import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;



@RestControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler extends Exception {

	
	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<Object> exception(ItemNotFoundException exception) {
		log.error("Handling DeliveryNotFoundException");
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicateException.class)
	public ResponseEntity<Object> exception(DuplicateException exception) {
		log.error("Handling DuplicateExceptoin");
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResource> handleIncorrectRequestBody(HttpMessageNotReadableException exception) {
        log.error("Incorrectly formatted body", exception);
        ErrorResource errorResource = new ErrorResource(400, "parseError", "Could not parse the request body.");
        return new ResponseEntity<>(errorResource, HttpStatus.BAD_REQUEST);
    }
	
	
	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<Object> exception(InvalidDataException exception) {
		log.error("Handling InvalidDataException");
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
}
