package com.tailan.ledger.api.model.repositories;

import com.tailan.ledger.api.model.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    boolean existsByExternalId(String externalId);
    Transaction findByExternalId(String externalId);
}
