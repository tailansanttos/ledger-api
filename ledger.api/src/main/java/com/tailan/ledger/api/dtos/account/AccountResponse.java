package com.tailan.ledger.api.dtos.account;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountResponse(UUID id, String holderName, BigDecimal balance) {
}
