package com.example.project.repository.backup;

import com.example.project.repository.entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BackupEntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findByUserEmail(String email);
}