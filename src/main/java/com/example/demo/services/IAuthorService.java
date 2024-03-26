package com.example.demo.services;

import com.example.demo.dto.AuthorDTO;

import java.util.List;

public interface IAuthorService {
    List<AuthorDTO> findAll();
    AuthorDTO findById(Long id);
    AuthorDTO deleteAuthor(Long id);
    AuthorDTO createAuthor(AuthorDTO authorDTO);
    AuthorDTO updateAuthor(AuthorDTO authorDTO, Long id);
}