package com.example.demo.dao.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "AUTHOR_ENTITY")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private String surname;
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "authorEntity", fetch = FetchType.LAZY)
    private Set<ArticleEntity> articleEntities = new HashSet<>();
}
