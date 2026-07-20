package com.ecommerce.exceptions;

import com.ecommerce.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            ResourceNotFoundException.class
    )
    public ResponseEntity<ErrorResponse>
    handleResourceNotFoundException(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        log.error(
                "ResourceNotFoundException : {}",
                ex.getMessage()
        );

        ErrorResponse response =
                ErrorResponse.builder()
                        .timestamp(
                                LocalDateTime.now()
                        )
                        .status(
                                HttpStatus.NOT_FOUND.value()
                        )
                        .error(
                                HttpStatus.NOT_FOUND
                                        .getReasonPhrase()
                        )
                        .message(
                                ex.getMessage()
                        )
                        .path(
                                request.getRequestURI()
                        )
                        .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(
            BadRequestException.class
    )
    public ResponseEntity<ErrorResponse>
    handleBadRequestException(
            BadRequestException ex,
            HttpServletRequest request) {

        log.error(
                "BadRequestException : {}",
                ex.getMessage()
        );

        ErrorResponse response =
                ErrorResponse.builder()
                        .timestamp(
                                LocalDateTime.now()
                        )
                        .status(
                                HttpStatus.BAD_REQUEST.value()
                        )
                        .error(
                                HttpStatus.BAD_REQUEST
                                        .getReasonPhrase()
                        )
                        .message(
                                ex.getMessage()
                        )
                        .path(
                                request.getRequestURI()
                        )
                        .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(
            UnauthorizedException.class
    )
    public ResponseEntity<ErrorResponse>
    handleUnauthorizedException(
            UnauthorizedException ex,
            HttpServletRequest request) {

        log.error(
                "UnauthorizedException : {}",
                ex.getMessage()
        );

        ErrorResponse response =
                ErrorResponse.builder()
                        .timestamp(
                                LocalDateTime.now()
                        )
                        .status(
                                HttpStatus.UNAUTHORIZED.value()
                        )
                        .error(
                                HttpStatus.UNAUTHORIZED
                                        .getReasonPhrase()
                        )
                        .message(
                                ex.getMessage()
                        )
                        .path(
                                request.getRequestURI()
                        )
                        .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(
            Exception.class
    )
    public ResponseEntity<ErrorResponse>
    handleGlobalException(
            Exception ex,
            HttpServletRequest request) {

        log.error(
                "Unhandled Exception : ",
                ex
        );

        ErrorResponse response =
                ErrorResponse.builder()
                        .timestamp(
                                LocalDateTime.now()
                        )
                        .status(
                                HttpStatus.INTERNAL_SERVER_ERROR
                                        .value()
                        )
                        .error(
                                HttpStatus.INTERNAL_SERVER_ERROR
                                        .getReasonPhrase()
                        )
                        .message(
                                ex.getMessage()
                        )
                        .path(
                                request.getRequestURI()
                        )
                        .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR
        );


    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public ResponseEntity<Map<String, String>>
    handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors =
                new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        ));

        return ResponseEntity
                .badRequest()
                .body(errors);
    }
}