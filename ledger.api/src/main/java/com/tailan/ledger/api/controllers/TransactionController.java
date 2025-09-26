package com.tailan.ledger.api.controllers;

import com.tailan.ledger.api.dtos.transaction.TransactionRequest;
import com.tailan.ledger.api.dtos.transaction.TransactionResponse;
import com.tailan.ledger.api.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

  @GetMapping("/transaction/extract/{accountId}")
  public ResponseEntity<Page<TransactionResponse>> getExtract(@PathVariable("accountId")UUID accountId, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {

      Page<TransactionResponse> getExtract = transactionService.getExtract(accountId, page, size);
      return ResponseEntity.status(HttpStatus.OK).body(getExtract);
  }

}
