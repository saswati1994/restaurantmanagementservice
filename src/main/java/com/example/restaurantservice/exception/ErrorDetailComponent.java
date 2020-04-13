package com.example.restaurantservice.exception;

import org.springframework.validation.FieldError;

import lombok.Data;

@Data
public class ErrorDetailComponent {
    private String field;
    private String message;
    private String code;
    
    public ErrorDetailComponent(FieldError fe) {
        this.field = fe.getField();
        this.message = fe.getDefaultMessage();
        this.code = fe.getCode();
    }
    
}

