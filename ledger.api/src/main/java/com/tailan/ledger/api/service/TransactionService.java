package com.tailan.ledger.api.service;

import com.tailan.ledger.api.dtos.transaction.TransactionRequest;
import com.tailan.ledger.api.dtos.transaction.TransactionResponse;

public interface TransactionService {
    public TransactionResponse depositAccount(Long accountId, TransactionRequest transactionRequest);
    public TransactionResponse accountWithdrawal(Long accountId, TransactionRequest transactionRequest);
}
