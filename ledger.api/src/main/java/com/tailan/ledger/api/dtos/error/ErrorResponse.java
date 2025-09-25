package com.tailan.ledger.api.dtos.error;

public record ErrorResponse(String message, String error, int status) {
}
