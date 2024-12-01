package com.example.project.repository.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private LocalDateTime publishedDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    public News(String title, String content, LocalDateTime publishedDate, Users user) {
        this.title = title;
        this.content = content;
        this.publishedDate = publishedDate;
        this.user = user;
    }
}