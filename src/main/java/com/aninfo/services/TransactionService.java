package com.aninfo.services;

import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.services.AccountService;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Transactional
    public Transaction createTransaction(Long cbu, Double sum, Transaction.TransactionType type) {
        Account account = accountRepository.findAccountByCbu(cbu);
        if (account == null) {
            throw new IllegalArgumentException("Cuenta no encontrada");
        }

        if (type == Transaction.TransactionType.DEPOSIT) {
            accountService.deposit(account.getCbu(), sum);
        } else {
            accountService.withdraw(account.getCbu(), sum);
        }

        Transaction transaction = new Transaction(type, sum);
        transactionRepository.save(transaction);

        return transaction;
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Collection<Transaction> getTransactionsAccountsByCbu(Long cbu) {
        Account account = accountRepository.findAccountByCbu(cbu);
        if (account == null) {
            throw new IllegalArgumentException("Cuenta no encontrada");
        }
        return account.getTransactions();
    }
}

