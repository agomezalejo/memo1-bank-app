package com.aninfo.controllers;

import com.aninfo.model.Account;
import com.aninfo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("")
    public Collection<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/{cbu}")
    public ResponseEntity<Account> getAccount(@PathVariable Long cbu) {
        Optional<Account> accountOptional = accountService.findById(cbu);
        return ResponseEntity.of(accountOptional);
    }

    @PutMapping("/{cbu}")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account, @PathVariable Long cbu) {
        Optional<Account> accountOptional = accountService.findById(cbu);

        if (!accountOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        account.setCbu(cbu);
        accountService.save(account);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cbu}")
    public void deleteAccount(@PathVariable Long cbu) {
        accountService.deleteById(cbu);
    }

    @PutMapping("/{cbu}/withdraw")
    public Account withdraw(@PathVariable Long cbu, @RequestParam Double sum) {
        return accountService.withdraw(cbu, sum);
    }

    @PutMapping("/{cbu}/deposit")
    public Account deposit(@PathVariable Long cbu, @RequestParam Double sum) {
        return accountService.deposit(cbu, sum);
    }
}
