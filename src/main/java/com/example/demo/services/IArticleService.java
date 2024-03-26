package com.example.demo.services;

import com.example.demo.dto.ArticleDTO;

import java.util.List;

public interface IArticleService {
    List<ArticleDTO> findAll();
    ArticleDTO findById(Long id);
    ArticleDTO deleteArticle(Long id);
    ArticleDTO createArticle(ArticleDTO articleDTO);
    ArticleDTO updateArticle(ArticleDTO articleDTO, Long id);
}
