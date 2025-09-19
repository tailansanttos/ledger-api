package com.tailan.ledger.api.service;

import com.tailan.ledger.api.dtos.account.AccountRequest;
import com.tailan.ledger.api.dtos.account.AccountResponse;

public interface AccountService {
    public AccountResponse createAccount(AccountRequest accountRequest);
}
