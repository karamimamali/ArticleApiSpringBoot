package com.example.demo.rest.v1;

import com.example.demo.dto.AuthorDTO;
import com.example.demo.services.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AuthorController {

    private final IAuthorService authorService;


    @GetMapping("/authors")
    public List<AuthorDTO> findAll() {
        return authorService.findAll();
    }

    @GetMapping("/authors/{id}")
    public AuthorDTO findById(@PathVariable Long id) {
        return authorService.findById(id);
    }

    @DeleteMapping("/authors/{id}")
    public AuthorDTO deleteAuthor(@PathVariable Long id) {
        return authorService.deleteAuthor(id);
    }

    @PostMapping("/authors")
    public AuthorDTO createAuthor(@RequestBody AuthorDTO authorDTO) {
        return authorService.createAuthor(authorDTO);
    }

    @PutMapping("/authors/{id}")
    public AuthorDTO updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDTO) {
        return authorService.updateAuthor(authorDTO, id);
    }
}
