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
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
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

        if (transactionRequest.externalId() != null){
            boolean exists = transactionRepository.existsByExternalId(transactionRequest.externalId());
            if (exists){
                Transaction existsTransaction = transactionRepository.findByExternalId(transactionRequest.externalId());
                return new TransactionResponse(existsTransaction.getId(), existsTransaction.getTransactionType(), existsTransaction.getValue(), existsTransaction.getAccount().getId(), LocalDateTime.now());
            }
        }

        Account account = getAccount(accountId);

        Transaction transaction = new Transaction();
        transaction.setValue(valueTransaction);
        transaction.setTransactionType(TransactionType.INCOME);
        transaction.setAccount(account);
        transaction.setDateTime(LocalDateTime.now());

        accountService.addBalance(account, valueTransaction);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return new TransactionResponse(savedTransaction.getId(), savedTransaction.getTransactionType(), savedTransaction.getValue(), savedTransaction.getAccount().getId(), savedTransaction.getDateTime());
    }

    @Transactional
    @Override
    public TransactionResponse accountWithdrawal(UUID accountId, TransactionRequest transactionRequest) {

        BigDecimal valueTransaction = transactionRequest.value();
        if (valueTransaction.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionException("O valor da transação deve ser maior que zero");
        }

        if (transactionRequest.externalId() != null){
            boolean exists = transactionRepository.existsByExternalId(transactionRequest.externalId());
            if (exists){
                Transaction existsTransaction = transactionRepository.findByExternalId(transactionRequest.externalId());
                return new TransactionResponse(existsTransaction.getId(), existsTransaction.getTransactionType(), existsTransaction.getValue(), existsTransaction.getAccount().getId(), LocalDateTime.now());
            }
        }

        Account account = getAccount(accountId);
        Transaction transaction = new Transaction();
        transaction.setValue(valueTransaction);
        transaction.setAccount(account);
        transaction.setDateTime(LocalDateTime.now());
        transaction.setTransactionType(TransactionType.EXPENSE);

        accountService.subtractBalance(account, valueTransaction);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return new TransactionResponse(savedTransaction.getId(),
                savedTransaction.getTransactionType(), savedTransaction.getValue(),
                savedTransaction.getAccount().getId(), savedTransaction.getDateTime());
    }


    public Account getAccount(UUID accountId) {
        return accountService.getAccount(accountId);
    }
}
