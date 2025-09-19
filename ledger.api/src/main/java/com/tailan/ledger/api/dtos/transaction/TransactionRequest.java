package com.tailan.ledger.api.dtos.transaction;

import java.math.BigDecimal;

public record TransactionRequest(BigDecimal value) {
}
