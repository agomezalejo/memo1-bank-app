package com.aninfo.controllers;

import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.services.TransactionService;
import com.aninfo.dto.TransactionRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/{cbu}")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction createTransaction(@PathVariable("cbu") Long cbu, @RequestBody TransactionRequestDto requestDto) {
        double sum = requestDto.getSum();
        Transaction.TransactionType type = requestDto.getType();
        return transactionService.createTransaction(cbu, sum, type);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
        Optional<Transaction> transactionOptional = transactionService.getTransactionById(id);
        return ResponseEntity.of(transactionOptional);
    }

    @GetMapping("/accounts/{cbu}")
    public Collection<Transaction> getTransactionsByAccountCbu(@PathVariable Long cbu) {
        return transactionService.getTransactionsAccountsByCbu(cbu);
    }

    @DeleteMapping("/{transactionId}")
    public void deleteTransaction(@PathVariable Long transactionId, @RequestParam Long cbu) {
        transactionService.deleteTransaction(transactionId, cbu);
    }

}

