package com.example.project.repository.backup;


import com.example.project.repository.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BackupUsersRepository extends JpaRepository<Users, Long>, BackupOneRepo {
    Users findByLogin(String login);

    List<Users> findByRoleId(Long roleId);

    Users findByEmail(String email);
}
