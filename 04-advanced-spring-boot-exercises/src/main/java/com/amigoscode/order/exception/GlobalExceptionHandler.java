package com.amigoscode.order.exception;

// TODO: 7 - Create a global exception handler:
//  - Annotate with @ControllerAdvice
//  - Add an @ExceptionHandler(OrderNotFoundException.class) method
//    that returns ResponseEntity<ApiError>
//  - Build an ApiError with the request path, exception message, 404 status, and current timestamp
//  Hint: inject HttpServletRequest to get the request path

// TODO: 8 - Add another @ExceptionHandler for MethodArgumentNotValidException:
//  - Extract field errors from the exception
//  - Build a message string with all field errors: "field: message; field: message"
//  - Return a 400 Bad Request response with ApiError
//  Hint: ex.getBindingResult().getFieldErrors()

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiError> handleOrderNotFound(OrderNotFoundException ex, HttpServletRequest req){
        ApiError error = new ApiError(req.getPathInfo(), ex.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleGlobalException(MethodArgumentNotValidException ex, HttpServletRequest req){

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream().map(e -> e.getField() + " : " + e.getObjectName())
                .toList();

        ApiError apiError = new ApiError(req.getPathInfo(), errors.toString(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
}
