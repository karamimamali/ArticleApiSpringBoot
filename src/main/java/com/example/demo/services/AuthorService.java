package com.example.demo.services;

import com.example.demo.domain.Author;
import com.example.demo.dto.AuthorDTO;

public interface AuthorService {
    Iterable<AuthorDTO> findAll();
    AuthorDTO findById(Long id);
    Boolean deleteAuthor(Long id);
    Author createAuthor(Author author);
    AuthorDTO updateAuthor(AuthorDTO authorDTO, Long id);

    Author convertToEntity(AuthorDTO authorDTO);
    AuthorDTO convertToDTO(Author author);
}