package com.example.demo.services;

import com.example.demo.dao.entities.ArticleEntity;
import com.example.demo.dao.entities.AuthorEntity;
import com.example.demo.dao.repositories.ArticleRepository;
import com.example.demo.dao.repositories.AuthorRepository;
import com.example.demo.dto.ArticleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService implements IArticleService {
    private final ArticleRepository articleRepository;
    private final AuthorRepository authorRepository;


    @Override
    public List<ArticleDTO> findAll() {
            return articleRepository
                        .findAll()
                        .stream()
                        .filter(article -> !article.getIsDeleted())
                        .map(this::convertToDTO)
                        .toList();
    }

    @Override
    public ArticleDTO findById(Long id) {
        return articleRepository
                .findById(id)
                .filter(article -> !article.getIsDeleted())
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found with id: " + id));
    }


    @Override
    public ArticleDTO deleteArticle(Long id) {
        return articleRepository.findById(id)
                .map(articleEntity -> {
                    articleEntity.setIsDeleted(true);
                    articleRepository.save(articleEntity);
                    return convertToDTO(articleEntity);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found with id: " + id));
    }

    @Override
    public ArticleDTO createArticle(ArticleDTO articleDTO) {
        return Optional.ofNullable(articleDTO)
                .map(this::convertToEntity)
                .map(articleRepository::save)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Article cannot be null"));
    }

    @Override
    public ArticleDTO updateArticle(ArticleDTO articleDTO, Long id) {
        return articleRepository.findById(id)
                .map(articleEntity -> {
                    articleEntity.setTitle(articleDTO.getTitle());
                    articleEntity.setContent(articleDTO.getContent());
                    return articleRepository.save(articleEntity);
                })
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found with id: " + id));
    }

    private ArticleEntity convertToEntity(ArticleDTO articleDTO) {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setTitle(articleDTO.getTitle());
        articleEntity.setContent(articleDTO.getContent());
        Optional<AuthorEntity> optionalAuthor = authorRepository.findById(articleDTO.getAuthorId());
        if (optionalAuthor.isPresent()) {
            AuthorEntity authorEntity = optionalAuthor.get();
            articleEntity.setAuthorEntity(authorEntity);
        } else {
            throw new RuntimeException("Author not found for ID: " + articleDTO.getAuthorId());
        }

        return articleEntity;
    }


    private ArticleDTO convertToDTO(ArticleEntity articleEntity) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(articleEntity.getId());
        articleDTO.setTitle(articleEntity.getTitle());
        articleDTO.setContent(articleEntity.getContent());
        articleDTO.setAuthorId(articleEntity.getAuthorEntity().getId());
        return articleDTO;
    }
}
