package com.tailan.ledger.api.controllers;

import com.tailan.ledger.api.dtos.transaction.TransactionRequest;
import com.tailan.ledger.api.dtos.transaction.TransactionResponse;
import com.tailan.ledger.api.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
  private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transaction/deposit/{accountId}")
    public ResponseEntity<TransactionResponse> depositAccount(@PathVariable("accountId")UUID accountId, @RequestBody TransactionRequest transactionRequest) {
      TransactionResponse transactionResponse = transactionService.depositAccount(accountId, transactionRequest);
      return ResponseEntity.status(HttpStatus.OK).body(transactionResponse);
    }

  @PostMapping("/transaction/withdrawal/{accountId}")
  public ResponseEntity<TransactionResponse> withdrawalAccount(@PathVariable("accountId")UUID accountId, @RequestBody TransactionRequest transactionRequest) {
    TransactionResponse transactionResponse = transactionService.accountWithdrawal(accountId, transactionRequest);
    return ResponseEntity.status(HttpStatus.OK).body(transactionResponse);
  }

}
