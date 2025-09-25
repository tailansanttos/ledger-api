package com.tailan.ledger.api.exceptions.handle;

import com.tailan.ledger.api.dtos.error.ErrorResponse;
import com.tailan.ledger.api.exceptions.exception.ContaNotFoundException;
import com.tailan.ledger.api.exceptions.exception.InvalidTransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Globalhandle {
    @ExceptionHandler(ContaNotFoundException.class)
    public ResponseEntity<ErrorResponse> contaNotFound(ContaNotFoundException e) {
        ErrorResponse erro = new ErrorResponse("Conta not found!", e.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<ErrorResponse> invalidTransaction(InvalidTransactionException e) {
        ErrorResponse erro = new ErrorResponse("Invalid transaction!", e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }
}
