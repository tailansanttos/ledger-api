package com.tailan.ledger.api.service.impl;

import com.tailan.ledger.api.dtos.account.AccountRequest;
import com.tailan.ledger.api.dtos.account.AccountResponse;
import com.tailan.ledger.api.model.domain.Account;
import com.tailan.ledger.api.model.repositories.AccountRepository;
import com.tailan.ledger.api.service.AccountService;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

@Service
public class AccountServiceIml implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceIml(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountResponse createAccount(AccountRequest accountRequest) {
        Account account = new Account();
        account.setHolderName(accountRequest.holderName());
        account.setBalance(new BigDecimal(0));
        Account savedAccount = accountRepository.save(account);
        return new AccountResponse(savedAccount.getId(),
                savedAccount.getHolderName(),
                savedAccount.getBalance());
    }

    @Override
    public Account getAccount(Long accountId) {
        Account account = null;
        try {
            account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        } catch (AccountNotFoundException e) {
            throw new RuntimeException(e);
        }
        return account;
    }


    @Override
    public void addBalance(Account account, BigDecimal balance) {
        account.setBalance(account.getBalance().add(balance));
    }

    @Override
    public void subtractBalance(Account account, BigDecimal balance) {
        if (account.getBalance().compareTo(balance) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para saque.");
        }
        account.setBalance(account.getBalance().subtract(balance));
    }
}
