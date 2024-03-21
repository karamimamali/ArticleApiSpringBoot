package com.example.demo.services;

import com.example.demo.domain.Author;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Iterable<AuthorDTO> findAll() {
        Iterable<Author> authors = authorRepository.findAll();
        List<AuthorDTO> authorDTOs = new ArrayList<>();
        for (Author author : authors) {
            if (!author.getIsDeleted()) {
                AuthorDTO authorDTO = convertToDTO(author);
                authorDTOs.add(authorDTO);
            }
        }
        return authorDTOs;
    }

    @Override
    public AuthorDTO findById(Long id) {
        Optional<Author> optionalArticle = authorRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Author author = optionalArticle.get();
            if (author.getIsDeleted()) {
                return null;
            }
            return convertToDTO(author);
        }
        return null;
    }

    @Override
    public Boolean deleteAuthor(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            author.setIsDeleted(true);
            authorRepository.save(author);
            return true;
        }
        return false;
    }

    @Override
    public Author createAuthor(Author author) {
        authorRepository.save(author);
        return author;
    }

    @Override
    public AuthorDTO updateAuthor(AuthorDTO authorDTO, Long id) {
        if (!authorRepository.existsById(authorDTO.getId())) {
            throw new EntityNotFoundException("Article not found with id: " + id);
        }

        Author authorToUpdate = convertToEntity(authorDTO);

        authorToUpdate.setId(id);

        Author updatedAuthor = authorRepository.save(authorToUpdate);


        return convertToDTO(updatedAuthor);
    }


    public Author convertToEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setSurname(authorDTO.getSurname());

        return author;
    }


    public AuthorDTO convertToDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setSurname(author.getSurname());
        return authorDTO;
    }
}
