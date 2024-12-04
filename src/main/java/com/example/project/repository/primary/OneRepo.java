package com.example.project.repository.primary;

import com.example.project.repository.entities.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OneRepo {
    List<Users> findAll();
}
