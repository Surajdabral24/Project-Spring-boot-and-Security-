package com.example.peter.exception;

import com.example.peter.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerUserAlreadyExists.class)
    public ResponseEntity<ErrorResponseDto> handleUserAlreadyExists(CustomerUserAlreadyExists exception, WebRequest request) {
        ErrorResponseDto dto = new ErrorResponseDto(
        request.getDescription(false),
        com.example.peter.emun.HttpStatus.BAD_REQUEST,
        exception.getMessage(),
        LocalDateTime.now());
        return new ResponseEntity<>(dto,HttpStatus.BAD_REQUEST);

    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> validationError = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String validateMsg = error.getDefaultMessage();
            validationError.put(fieldName,validateMsg);
        });

        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }
}

