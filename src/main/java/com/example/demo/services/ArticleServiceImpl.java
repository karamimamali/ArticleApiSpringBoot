package com.example.demo.services;

import com.example.demo.domain.Article;
import com.example.demo.domain.Author;
import com.example.demo.dto.ArticleDTO;
import com.example.demo.repositories.ArticleRepository;
import com.example.demo.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final AuthorRepository authorRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository, AuthorRepository authorRepository) {
        this.articleRepository = articleRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Iterable<ArticleDTO> findAll() {
        Iterable<Article> articles = articleRepository.findAll();
        List<ArticleDTO> articleDTOs = new ArrayList<>();
        for (Article article : articles) {
            if (!article.getIsDeleted()) {
                ArticleDTO articleDTO = convertToDTO(article);
                articleDTOs.add(articleDTO);
            }
        }
        return articleDTOs;
    }

    @Override
    public ArticleDTO findById(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            if (article.getIsDeleted()) {
                return null;
            }
            return convertToDTO(article);
        }
        return null;
    }

    @Override
    public ArticleDTO findByTitle(String title) {
        Article article = articleRepository.findByTitle(title);
        if (article != null && !article.getIsDeleted()) {
            return convertToDTO(article);
        }
        return null;
    }

    @Override
    public Boolean deleteArticle(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            article.setIsDeleted(true);
            articleRepository.save(article);
            return true;
        }
        return false;
    }

    @Override
    public Article createArticle(Article article) {
        articleRepository.save(article);
        return article;
    }

    @Override
    public ArticleDTO updateArticle(ArticleDTO articleDTO, Long id) {

        if (!articleRepository.existsById(articleDTO.getId())) {
            throw new EntityNotFoundException("Article not found with id: " + id);
        }


        Article articleToUpdate = convertToEntity(articleDTO);

        articleToUpdate.setId(id);

        Article updatedArticle = articleRepository.save(articleToUpdate);


        return convertToDTO(updatedArticle);
    }

    public Article convertToEntity(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        Optional<Author> optionalAuthor = authorRepository.findById(articleDTO.getAuthor_id());
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            article.setAuthor(author);
        } else {
            throw new RuntimeException("Author not found for ID: " + articleDTO.getAuthor_id());
        }

        return article;
    }


    public ArticleDTO convertToDTO(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        articleDTO.setAuthor_id(article.getAuthor().getId());
        return articleDTO;
    }
}
