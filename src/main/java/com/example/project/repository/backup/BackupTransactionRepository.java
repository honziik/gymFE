package com.example.project.repository.backup;

import com.example.project.repository.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BackupTransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserEmail(String email);
}
