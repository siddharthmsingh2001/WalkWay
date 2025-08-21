package com.walkway.account_service.exception;

import com.walkway.account_service.dto.ErrorResponseDto;
import com.walkway.account_service.exception.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RealmAccountAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateRealmAccount(RealmAccountAlreadyExistException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.REALM_ACCOUNT_EXISTS_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(ServiceAccountAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateServiceAccount(ServiceAccountAlreadyExistException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.SERVICE_ACCOUNT_EXISTS_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(ServiceAccountNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleAbsentServiceAccount(ServiceAccountNotFoundException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.SERVICE_ACCOUNT_MISSING_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(AccountProfileAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateAccountProfile(AccountProfileAlreadyExistException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.ACCOUNT_PROFILE_EXISTS_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(AccountProfileNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleAbsentAccountProfile(AccountProfileNotFoundException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.ACCOUNT_PROFILE_MISSING_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationErrors(MethodArgumentNotValidException cause, WebRequest request) {
        StringBuilder sb = new StringBuilder();
        cause.getBindingResult().getFieldErrors().forEach(error ->
                sb.append("[").append(error.getField()).append(": ").append(error.getDefaultMessage()).append("] ")
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.VALIDATION_EXCEPTION,
                                sb.toString().trim()
                        )
                );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleJsonParseError(HttpMessageNotReadableException cause, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.JSON_PARSING_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }
}
