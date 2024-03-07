package com.hire10x.team.Exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TeamExceptions extends RuntimeException {
    @ExceptionHandler(TeamDuplicateException.class)
    public ResponseEntity<Map> handleBadRequestException(TeamDuplicateException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("ErrorMessage", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("ErrorMessage", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DataAccessResourceFailureException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessResourceFailureException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        String errorMessage = "An unexpected error occurred: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


