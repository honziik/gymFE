package com.example.project.repository.backup;

import com.example.project.repository.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackupRoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
