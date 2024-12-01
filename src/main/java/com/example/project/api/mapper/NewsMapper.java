package com.example.project.api.mapper;

import com.example.project.api.dto.NewsDto;
import com.example.project.repository.entities.News;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NewsMapper {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static List<NewsDto> MAP(List<News> newsList) {
        List<NewsDto> newsDtoList = new ArrayList<>();
        newsList.forEach(news -> {
            newsDtoList.add(new NewsDto(news.getTitle(), news.getUser().getLogin(), news.getContent(), news.getPublishedDate().format(FORMATTER)));
        });
        return newsDtoList;
    }

}
