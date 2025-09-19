package com.tailan.ledger.api.service;

import com.tailan.ledger.api.dtos.account.AccountRequest;
import com.tailan.ledger.api.dtos.account.AccountResponse;
import com.tailan.ledger.api.model.domain.Account;

import java.math.BigDecimal;

public interface AccountService {
    public AccountResponse createAccount(AccountRequest accountRequest);
    public Account getAccount(Long accountId);

    void addBalance(Account account, BigDecimal balance);

    void subtractBalance(Account account, BigDecimal balance);
}
