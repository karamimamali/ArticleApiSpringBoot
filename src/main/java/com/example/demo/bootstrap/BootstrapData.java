package com.example.demo.bootstrap;

import com.example.demo.repositories.ArticleRepository;
import com.example.demo.repositories.AuthorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner{

    private final AuthorRepository authorRepository;
    private final ArticleRepository articleRepository;

    public BootstrapData(AuthorRepository authorRepository, ArticleRepository articleRepository) {
        this.authorRepository = authorRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        Author author1 = new Author();
//        author1.setName("Marks");
//        author1.setSurname("Charles");
//
//        // Save author first to generate ID
//        authorRepository.save(author1);
//
//        // Create articles
//        Article article1 = new Article();
//        article1.setTitle("First");
//        article1.setContent("This is my first content");
//        article1.setAuthor(author1);
//
//        Article article2 = new Article();
//        article2.setTitle("Second");
//        article2.setContent("This is my second content");
//        article2.setAuthor(author1);
//
//        // Save articles
//        articleRepository.save(article1);
//        articleRepository.save(article2);
    }
    
}
