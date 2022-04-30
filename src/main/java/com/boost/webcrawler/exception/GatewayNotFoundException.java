package com.boost.webcrawler.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "gateway not found")
public class GatewayNotFoundException extends RuntimeException{
}
