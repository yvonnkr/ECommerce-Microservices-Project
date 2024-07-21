package com.yvolabs.ecommerce.handler;

import com.yvolabs.ecommerce.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 18/07/2024
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handle(BusinessException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(getErrorResponse(e));
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handle(EntityNotFoundException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(getErrorResponse(e));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(getErrorResponse(e));
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<?> handleValidationException(HandlerMethodValidationException e) {

        // get errors from list
        var errorList = e.getAllValidationResults()
                .stream()
                .map(ParameterValidationResult::getResolvableErrors)
                .flatMap(Collection::stream)
                .map(MessageSourceResolvable::getDefaultMessage)
                .toList();

        // set errors as hashmap
        var messageMap = new HashMap<>();
        var msgNum = 1;
        for (var error : errorList) {
            messageMap.put(msgNum, error);
            msgNum++;
        }

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(messageMap);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnhandledExceptions(Exception e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(getErrorResponse(e));
    }

    private ErrorResponse getErrorResponse(Exception e) {
        return ErrorResponse.builder()
                .errors(Map.of("message", e.getMessage()))
                .build();
    }
}
