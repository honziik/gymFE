package com.example.project.repository.backup;

import com.example.project.repository.entities.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackupClassRepository extends JpaRepository<Class, Long> {
    Class findByTitle(String title);
}
