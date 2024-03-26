package com.example.demo.services;

import com.example.demo.dao.entities.AuthorEntity;
import com.example.demo.dao.repositories.AuthorRepository;
import com.example.demo.dto.AuthorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService implements IAuthorService {
    private final AuthorRepository authorRepository;


    @Override
    public List<AuthorDTO> findAll() {
        return authorRepository
                .findAll()
                .stream()
                .filter(author -> !author.getIsDeleted()) // Filter for articles that are not deleted
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public AuthorDTO findById(Long id) {
        return authorRepository
                .findById(id)
                .filter(author -> !author.getIsDeleted())
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found with id: " + id));
    }

    @Override
    public AuthorDTO deleteAuthor(Long id) {
        return authorRepository.findById(id)
                .map(articleEntity -> {
                    articleEntity.setIsDeleted(true);
                    authorRepository.save(articleEntity);
                    return convertToDTO(articleEntity);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found with id: " + id));
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        return Optional.ofNullable(authorDTO)
                .map(this::convertToEntity)
                .map(authorRepository::save)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author cannot be null"));
    }

    @Override
    public AuthorDTO updateAuthor(AuthorDTO authorDTO, Long id) {
        return authorRepository.findById(id)
                .map(authorEntity -> {
                    authorEntity.setName(authorDTO.getName());
                    authorEntity.setSurname(authorDTO.getSurname());
                    return authorRepository.save(authorEntity);
                })
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found with id: " + id));
    }


    private AuthorEntity convertToEntity(AuthorDTO authorDTO) {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName(authorDTO.getName());
        authorEntity.setSurname(authorDTO.getSurname());

        return authorEntity;
    }


    private AuthorDTO convertToDTO(AuthorEntity authorEntity) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(authorEntity.getId());
        authorDTO.setName(authorEntity.getName());
        authorDTO.setSurname(authorEntity.getSurname());
        return authorDTO;
    }
}
