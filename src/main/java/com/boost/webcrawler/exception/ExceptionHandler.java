package com.boost.webcrawler.exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info(ex.getClass().getName());

        StringBuffer buffer = new StringBuffer();
        buffer.append(ex.getMethod());
        buffer.append("  Method is Not Supported for This Request . Should be ");
        ex.getSupportedHttpMethods().forEach(s -> buffer.append(s + " "));

// add this Error data in MY DeveloperErrorMessage
        ErrorResponse developerErrorMessage = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), buffer.toString());


        return new ResponseEntity<>(developerErrorMessage, new HttpHeaders(), developerErrorMessage.getStatusCode());

    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
// show data on logger
        log.info(ex.getClass().getName());

// customize method message
        StringBuffer buffer = new StringBuffer();
        buffer.append(ex.getContentType());
        buffer.append("  Media Type is Not Supported for This Request . Should be ");
        ex.getSupportedMediaTypes().forEach(s -> buffer.append(s + " "));

// add this Error data in MY DeveloperErrorMessage
        ErrorResponse developerErrorMessage = new ErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getLocalizedMessage(), buffer.toString());


        return new ResponseEntity<>(developerErrorMessage, new HttpHeaders(), developerErrorMessage.getStatusCode());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        // show data on logger
        log.info(ex.getClass().getName());

// customize method message
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
// add this Error data in MY DeveloperErrorMessage
        ErrorResponse developerErrorMessage = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);


        return new ResponseEntity<>(developerErrorMessage, new HttpHeaders(), developerErrorMessage.getStatusCode());

    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info(ex.getClass().getName());
        //
        String error = ex.getParameterName() + " parameter is missing";
        // add this Error data in MY DeveloperErrorMessage
        ErrorResponse developerErrorMessage = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);


        return new ResponseEntity<>(developerErrorMessage, new HttpHeaders(), developerErrorMessage.getStatusCode());

    }


    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info(ex.getClass().getName());
        //
        String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

        ErrorResponse developerErrorMessage = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(developerErrorMessage, new HttpHeaders(), developerErrorMessage.getStatusCode());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessExcption.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> processMethodNotSuppotredException(BusinessExcption e,WebRequest request){

        ErrorResponse errorResponse= new ErrorResponse(e.getHttpStatus(),e.getMessage(),"");

        errorResponse.setErrorMessage(e.getMessage());
        errorResponse.setStatusCode(e.getHttpStatus());
        return new ResponseEntity<ErrorResponse>(errorResponse,e.getHttpStatus());
    }


    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        log.info(ex.getClass().getName());
        log.error("error", ex);
        //
        ErrorResponse developerErrorMessage = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");
        return new ResponseEntity<Object>(developerErrorMessage, new HttpHeaders(), developerErrorMessage.getStatusCode());
    }



}
