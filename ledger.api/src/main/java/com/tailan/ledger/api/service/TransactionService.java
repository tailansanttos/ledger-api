package com.tailan.ledger.api.service;

import com.tailan.ledger.api.dtos.transaction.TransactionRequest;
import com.tailan.ledger.api.dtos.transaction.TransactionResponse;

import java.util.UUID;

public interface TransactionService {
    public TransactionResponse depositAccount(UUID accountId, TransactionRequest transactionRequest);
    public TransactionResponse accountWithdrawal(UUID accountId, TransactionRequest transactionRequest);
}
