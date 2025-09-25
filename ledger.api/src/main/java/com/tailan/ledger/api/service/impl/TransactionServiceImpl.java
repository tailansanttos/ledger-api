package com.tailan.ledger.api.service.impl;

import com.tailan.ledger.api.dtos.transaction.TransactionRequest;
import com.tailan.ledger.api.dtos.transaction.TransactionResponse;
import com.tailan.ledger.api.exceptions.exception.InvalidTransactionException;
import com.tailan.ledger.api.model.domain.Account;
import com.tailan.ledger.api.model.domain.Transaction;
import com.tailan.ledger.api.model.enums.TransactionType;
import com.tailan.ledger.api.model.repositories.TransactionRepository;
import com.tailan.ledger.api.service.AccountService;
import com.tailan.ledger.api.service.TransactionService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    @Transactional
    @Override
    public TransactionResponse depositAccount(UUID accountId, TransactionRequest transactionRequest) {
        BigDecimal valueTransaction = transactionRequest.value();

        if (valueTransaction.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionException("O valor da transação deve ser maior que zero");
        }

        String externalId = transactionRequest.externalId();

        Transaction existingTransaction = transactionRepository.findByExternalId(externalId);
        if (existingTransaction != null) {
            log.warn("Transação idempotente detectada. ExternalId: {}. Retornando resultado anterior.", externalId);
            return  mapToResponse(existingTransaction);
        }

        Account account = getAccount(accountId);

        Transaction transaction = new Transaction();
        transaction.setValue(valueTransaction);
        transaction.setTransactionType(TransactionType.INCOME);
        transaction.setAccount(account);
        transaction.setExternalId(transactionRequest.externalId());
        transaction.setDateTime(LocalDateTime.now());

        accountService.addBalance(account, valueTransaction);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return mapToResponse(savedTransaction);
    }

    @Transactional
    @Override
    public TransactionResponse accountWithdrawal(UUID accountId, TransactionRequest transactionRequest) {

        BigDecimal valueTransaction = transactionRequest.value();
        if (valueTransaction.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionException("O valor da transação deve ser maior que zero");
        }

        String externalId = transactionRequest.externalId();

        Transaction existingTransaction = transactionRepository.findByExternalId(externalId);
        if (existingTransaction != null) {
            log.warn("Transação idempotente detectada. ExternalId: {}. Retornando resultado anterior.", externalId);
            return  mapToResponse(existingTransaction);
        }

        Account account = getAccount(accountId);
        Transaction transaction = new Transaction();
        transaction.setValue(valueTransaction);
        transaction.setAccount(account);
        transaction.setDateTime(LocalDateTime.now());
        transaction.setTransactionType(TransactionType.EXPENSE);
        transaction.setExternalId(transactionRequest.externalId());

        accountService.subtractBalance(account, valueTransaction);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return mapToResponse(savedTransaction);
    }


    public Account getAccount(UUID accountId) {
        return accountService.getAccount(accountId);
    }

    public TransactionResponse mapToResponse(Transaction transaction) {
        return new TransactionResponse
                (transaction.getId(),
                        transaction.getTransactionType(),
                        transaction.getValue(),
                        transaction.getAccount().getId(),
                        transaction.getDateTime());
    }
}
