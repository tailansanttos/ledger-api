package com.tailan.ledger.api.dtos.account;

import java.math.BigDecimal;

public record AccountResponse(Long id, String holderName, BigDecimal balance) {
}
