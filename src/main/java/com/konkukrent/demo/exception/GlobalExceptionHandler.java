package com.konkukrent.demo.exception;


import com.konkukrent.demo.exception.dto.ErrorResponse;
import com.konkukrent.demo.exception.dto.ValidationErrorResponse;
import com.konkukrent.demo.exception.exceptionClass.CustomException;
import com.konkukrent.demo.exception.properties.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<ErrorResponse> handleCustomException(
            CustomException e, HttpServletRequest request
    ) {
        return ErrorResponse.toResponseEntity(e.getErrorCode(), e.getRuntimeValue());
    }

    @ExceptionHandler(value = {
            BindException.class,
            MethodArgumentNotValidException.class
    })
    protected ResponseEntity<List<ValidationErrorResponse>> validationException(BindException e,
                                                                                HttpServletRequest request) {
        BindingResult bindingResult = e.getBindingResult();
        List<ValidationErrorResponse> errors = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            ValidationErrorResponse error = new ValidationErrorResponse(
                    fieldError.getField(),
                    fieldError.getDefaultMessage(),
                    fieldError.getRejectedValue()
            );
            errors.add(error);
        }
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(
            Exception e, HttpServletRequest request
    ) {
        return ErrorResponse.toResponseEntity(ErrorCode.SERVER_ERROR, e.getMessage());
    }
}

