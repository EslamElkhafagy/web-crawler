package com.boost.webcrawler.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessExcption extends Exception{

    private static final long serialVerionUID=1l;

    private String message;
    private HttpStatus httpStatus;


}
