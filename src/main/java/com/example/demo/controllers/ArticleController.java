package com.example.demo.controllers;

import com.example.demo.domain.Article;
import com.example.demo.dto.ArticleDTO;
import com.example.demo.services.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping("/article/show/all")
    public ResponseEntity<Iterable<ArticleDTO>> getAllArticles() {
        try {
            Iterable<ArticleDTO> articles = articleService.findAll();
            return new ResponseEntity<>(articles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/article/show/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long id) {
        try {
            ArticleDTO article = articleService.findById(id);
            if (article != null) {
                return new ResponseEntity<>(article, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/article/show")
    public ResponseEntity<ArticleDTO> getArticleByTitle(@RequestParam String title) {
        try {
            ArticleDTO article = articleService.findByTitle(title);
            if (article != null) {
                return new ResponseEntity<>(article, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/article/delete/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long id) {
        try {
            boolean deleted = articleService.deleteArticle(id);
            if (deleted) {
                return new ResponseEntity<>("Article deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Article not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete article", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/article/create")
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO) {
        try {
            Article article = articleService.convertToEntity(articleDTO);

            article = articleService.createArticle(article);

            ArticleDTO savedArticleDTO = articleService.convertToDTO(article);

            return new ResponseEntity<>(savedArticleDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("article/update/{id}")
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable Long id, @RequestBody ArticleDTO updatedArticleDTO) {
        try {
            ArticleDTO existingArticleDTO = articleService.findById(id);
            if (existingArticleDTO == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }


            existingArticleDTO.setTitle(updatedArticleDTO.getTitle());
            existingArticleDTO.setContent(updatedArticleDTO.getContent());


            ArticleDTO updatedArticle = articleService.updateArticle(existingArticleDTO, id);

            return new ResponseEntity<>(updatedArticle, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public String sayHello() {
        return "Welcome to my stupid app!";
    }
}
