package com.tailan.ledger.api.service;

import com.tailan.ledger.api.dtos.account.AccountRequest;
import com.tailan.ledger.api.dtos.account.AccountResponse;
import com.tailan.ledger.api.model.domain.Account;

import java.math.BigDecimal;
import java.util.UUID;

public interface AccountService {
    public AccountResponse createAccount(AccountRequest accountRequest);
    public Account getAccount(UUID accountId);

    public AccountResponse getAccountDto(UUID accountId);
    void addBalance(Account account, BigDecimal balance);

    void subtractBalance(Account account, BigDecimal balance);
}
