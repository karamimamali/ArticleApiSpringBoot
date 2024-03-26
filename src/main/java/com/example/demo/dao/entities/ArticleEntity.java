package com.example.demo.dao.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "ARTICLE_ENTITY")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String title;
    private String content;
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private AuthorEntity authorEntity;
}
