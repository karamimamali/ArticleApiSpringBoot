package com.example.demo.dao.repositories;

import com.example.demo.dao.entities.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
}
