package com.example.demo.repositories;

import com.example.demo.domain.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    Article findByTitle(String title);
}
