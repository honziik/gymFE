package com.example.project.repository.backup;

import com.example.project.repository.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BackupNewsRepository extends JpaRepository<News, Long> {
    @Query("SELECT n FROM News n")
    List<News> getAll();
}
