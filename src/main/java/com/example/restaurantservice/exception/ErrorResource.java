package com.example.restaurantservice.exception;

import java.util.List;

import lombok.Data;

@Data
public class ErrorResource {

    private Integer status; 
    private String code; 
    private String message;
    private List<ErrorDetailComponent> details;

    public ErrorResource(Integer status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
