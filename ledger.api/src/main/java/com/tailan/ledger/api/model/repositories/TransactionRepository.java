package com.tailan.ledger.api.model.repositories;

import com.tailan.ledger.api.model.domain.Account;
import com.tailan.ledger.api.model.domain.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    boolean existsByExternalId(String externalId);
    Transaction findByExternalId(String externalId);

    Page<Transaction> findAllByAccount(Account account, Pageable pageable);

}
