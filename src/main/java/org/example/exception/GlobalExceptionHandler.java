package org.example.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleRequestBodyException(MethodArgumentNotValidException exp){

        Map<String,String> requestBodyExceptions = new HashMap<>();

        for(FieldError err : exp.getBindingResult().getFieldErrors()){
            requestBodyExceptions.put(err.getField(), err.getDefaultMessage());
        }

        return ResponseEntity
                .status(400)
                .body(Objects.requireNonNull(requestBodyExceptions));
    }

    @ExceptionHandler(MailAuthenticationException.class)
    public ResponseEntity<?> mailAuthException(MailAuthenticationException exp){
        return ResponseEntity
                .status(500)
                .body("Authentication provided for sending email is incorrect.");
    }
}
