package com.tailan.ledger.api.service;

import com.tailan.ledger.api.dtos.transaction.TransactionRequest;
import com.tailan.ledger.api.dtos.transaction.TransactionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TransactionService {
    public TransactionResponse depositAccount(UUID accountId, TransactionRequest transactionRequest);
    public TransactionResponse accountWithdrawal(UUID accountId, TransactionRequest transactionRequest);

    public Page<TransactionResponse> getExtract(UUID accountId, int page, int size);
}
