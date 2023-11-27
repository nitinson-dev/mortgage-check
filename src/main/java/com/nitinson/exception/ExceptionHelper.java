package com.nitinson.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHelper extends ResponseEntityExceptionHandler {
    private static final Logger mlogger = LoggerFactory.getLogger(ExceptionHelper.class);

    @ExceptionHandler(value = {InvalidInputException.class})
    public ResponseEntity<Object> handleBusinessRuleException(InvalidInputException ex) {
        mlogger.error("Business Rule validation failed : {}", ex.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(new ErrorResponse(status, ex.getMessage()), status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable mostSpecificCause = ex.getMostSpecificCause();
        ErrorResponse errorResponse = new ErrorResponse(status, mostSpecificCause.getClass().getName() + " : " + mostSpecificCause.getMessage());
        return new ResponseEntity<>(errorResponse, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        mlogger.error("Method argument invalid: {}", exception.getMessage());
        return new ResponseEntity<>(
                new ErrorResponse(status, extractValidationMessage(exception)), status);
    }

    private String extractValidationMessage(MethodArgumentNotValidException exception) {
        String exceptionMessage = exception.getMessage();
        String[] messageParts = exceptionMessage.split(";");
        String finalPart = messageParts[messageParts.length - 1];

        return finalPart.trim().replaceAll("default message \\[|]]", "");
    }
}
