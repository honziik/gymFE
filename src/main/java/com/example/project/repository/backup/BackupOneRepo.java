package com.example.project.repository.backup;

import com.example.project.repository.entities.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BackupOneRepo {
    List<Users> findAll();
}
