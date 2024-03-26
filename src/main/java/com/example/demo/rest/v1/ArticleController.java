package com.example.demo.rest.v1;

import com.example.demo.dto.ArticleDTO;
import com.example.demo.services.IArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final IArticleService articleService;


    @GetMapping("/v1/articles")
    public List<ArticleDTO> findAll() {
        return articleService.findAll();
    }

    @GetMapping("/v1/articles/{id}")
    public ArticleDTO findById(@PathVariable Long id) {
        return articleService.findById(id);
    }

    @DeleteMapping("/v1/articles/{id}")
    public ArticleDTO deleteArticle(@PathVariable Long id) {
        return articleService.deleteArticle(id);
    }

    @PostMapping("/v1/articles")
    public ArticleDTO createArticle(@RequestBody ArticleDTO article) {
        return articleService.createArticle(article);
    }

    @PutMapping("/v1/articles/{id}")
    public ArticleDTO updateArticle(@PathVariable Long id, @RequestBody ArticleDTO updatedArticle) {
        return articleService.updateArticle(updatedArticle, id);
    }
}
