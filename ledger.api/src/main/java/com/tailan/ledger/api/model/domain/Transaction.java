package com.tailan.ledger.api.model.domain;

import com.tailan.ledger.api.model.enums.TransactionType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private LocalDateTime dateTime;
    private BigDecimal value;

    public Transaction() {
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Transaction(Long id, Account account, TransactionType transactionType, LocalDateTime dateTime, BigDecimal value) {
        this.id = id;
        this.account = account;
        this.transactionType = transactionType;
        this.dateTime = dateTime;
        this.value = value;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
