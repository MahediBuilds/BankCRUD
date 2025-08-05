package com.test.bankcrud.service;

import org.springframework.stereotype.Service;
import com.test.bankcrud.entity.BankAccount;
import com.test.bankcrud.repository.BankRepository;

@Service
public class BankService {
    private final BankRepository repository;

    public BankService(BankRepository repository) {
        this.repository = repository;
    }

    public BankAccount newAccount(String name, String number) {
        BankAccount account = new BankAccount(name, number);
        account.setBalance(0);
        return repository.save(account);
    }

    public BankAccount deposit(String accountNumber, double amount) {
        BankAccount account = repository.findByAccountNumber(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            return repository.save(account);
        }
        return null;
    }

    public BankAccount withdraw(String accountNumber, double amount) {
        BankAccount account = repository.findByAccountNumber(accountNumber);
        if (account != null && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            return repository.save(account);
        }
        return null;
    }
}
