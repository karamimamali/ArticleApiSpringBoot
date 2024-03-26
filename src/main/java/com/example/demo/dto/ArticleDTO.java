package com.example.demo.dto;

import lombok.Data;

@Data
public class ArticleDTO {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
}