package ua.mykola.footballmanager.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.mykola.footballmanager.exception.NotFoundException;
import ua.mykola.footballmanager.exception.TransferException;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<String> notFoundException(NotFoundException notFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundException.getMessage());
    }

    @ExceptionHandler(value = TransferException.class)
    public ResponseEntity<String> transferException(TransferException transferException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(transferException.getMessage());
    }
}
