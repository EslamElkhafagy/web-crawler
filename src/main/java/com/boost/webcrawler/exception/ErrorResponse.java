package com.boost.webcrawler.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private HttpStatus statusCode;
    private String errorMessage;
    private List<String> errors;


    public ErrorResponse(HttpStatus statusCode, String errorMessage, String errors) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.errors = Arrays.asList(errors);
    }

}
