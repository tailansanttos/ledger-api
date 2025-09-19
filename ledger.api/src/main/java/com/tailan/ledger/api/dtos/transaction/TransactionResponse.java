package com.tailan.ledger.api.dtos.transaction;

import com.tailan.ledger.api.model.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(Long transactionId,
                                  TransactionType transactionType,
                                  BigDecimal value,
                                  Long accountId,
                                  LocalDateTime dateTime) {
}
