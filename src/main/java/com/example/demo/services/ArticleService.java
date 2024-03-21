package com.example.demo.services;

import com.example.demo.domain.Article;
import com.example.demo.dto.ArticleDTO;

public interface ArticleService {
    Iterable<ArticleDTO> findAll();
    ArticleDTO findById(Long id);
    ArticleDTO findByTitle(String title);
    Boolean deleteArticle(Long id);
    Article convertToEntity(ArticleDTO articleDTO);
    ArticleDTO convertToDTO(Article article);
    Article createArticle(Article article);
    ArticleDTO updateArticle(ArticleDTO existingArticleDTO, Long id);
}
