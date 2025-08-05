package com.test.bankcrud.controller;

import com.test.bankcrud.entity.BankAccount;
import com.test.bankcrud.repository.BankRepository;
import com.test.bankcrud.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class BankController {

    private final BankRepository bankRepository;
    private final BankService bankService;

    @Autowired
    public BankController(BankRepository bankRepository, BankService bankService) {
        this.bankRepository = bankRepository;
        this.bankService = bankService;
    }

    @GetMapping
    public List<BankAccount> getAll() {
        return bankRepository.findAll();
    }

    @GetMapping("/{id}")
    public BankAccount getById(@PathVariable long id) {
        return bankRepository.findById(id).orElse(null);
    }

    @PostMapping
    public BankAccount create(@RequestBody BankAccount account) {
        return bankService.newAccount(account.getAccountHolderName(), account.getAccountNumber());
    }

    @PutMapping("/{accountNumber}/deposit")
    public BankAccount deposit(@PathVariable String accountNumber, @RequestParam double amount) {
        return bankService.deposit(accountNumber, amount);
    }

    @PutMapping("/{accountNumber}/withdraw")
    public BankAccount withdraw(@PathVariable String accountNumber, @RequestParam double amount) {
        return bankService.withdraw(accountNumber, amount);
    }

}
