package com.example.demo.controllers;

import com.example.demo.domain.Author;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author/show/all")
    public ResponseEntity<Iterable<AuthorDTO>> getAllAuthors() {
        try {
            Iterable<AuthorDTO> Authors = authorService.findAll();
            return new ResponseEntity<>(Authors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/author/show/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        try {
            AuthorDTO author = authorService.findById(id);
            if (author != null) {
                return new ResponseEntity<>(author, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/author/delete/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        try {
            boolean deleted = authorService.deleteAuthor(id);
            if (deleted) {
                return new ResponseEntity<>("Author deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Author not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete author", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/author/create")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        try {
            Author author = authorService.convertToEntity(authorDTO);

            author = authorService.createAuthor(author);

            AuthorDTO savedAuthorDTO = authorService.convertToDTO(author);

            return new ResponseEntity<>(savedAuthorDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("author/update/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO updatedAuthorDTO) {
        try {
            AuthorDTO existingAuthorDTO = authorService.findById(id);
            if (existingAuthorDTO == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }


            existingAuthorDTO.setName(updatedAuthorDTO.getName());
            existingAuthorDTO.setSurname(updatedAuthorDTO.getSurname());


            AuthorDTO updatedAuthor = authorService.updateAuthor(existingAuthorDTO, id);

            return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    

}
