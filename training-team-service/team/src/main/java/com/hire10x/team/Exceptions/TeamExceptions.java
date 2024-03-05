package com.hire10x.team.Exceptions;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TeamExceptions extends RuntimeException {
    @ExceptionHandler(TeamDuplicateException.class)
    public ResponseEntity<TeamApiError> handleBadRequestException() {
        TeamApiError teamApiError = TeamApiError.builder().errorID(400).errorCode(HttpStatus.BAD_REQUEST).errorMessage("Team name is already present").build();
        return new ResponseEntity<TeamApiError>(teamApiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        return new ResponseEntity<>("Validation error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

