package com.tailan.ledger.api.dtos.transaction;

import com.tailan.ledger.api.model.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionResponse(UUID transactionId,
                                  TransactionType transactionType,
                                  BigDecimal value,
                                  UUID accountId,
                                  LocalDateTime dateTime) {
}
