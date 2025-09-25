package com.tailan.ledger.api.exceptions.exception;

public class ContaNotFoundException extends RuntimeException {
    public ContaNotFoundException(String message) {
        super(message);
    }
}
