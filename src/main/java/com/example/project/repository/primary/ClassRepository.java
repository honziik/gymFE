package com.example.project.repository.primary;

import com.example.project.repository.entities.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, Long> {
    Class findByTitle(String title);
}
