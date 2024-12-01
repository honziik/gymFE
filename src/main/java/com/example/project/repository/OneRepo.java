package com.example.project.repository;

import com.example.project.repository.entities.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OneRepo {
    List<Users> findAll();
}
