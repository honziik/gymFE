package com.example.project.repository;


import com.example.project.repository.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long>, OneRepo{
    Users findByLogin(String login);

    List<Users> findByRoleId(Long roleId);

    Users findByEmail(String email);
}
