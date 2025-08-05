package com.test.bankcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.test.bankcrud.entity.BankAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankAccount, Long> {
    BankAccount findByAccountNumber(String accountNumber);
}
