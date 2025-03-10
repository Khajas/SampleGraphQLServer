package com.example.graphqlserver.controller;

import com.example.graphqlserver.dto.AuthorInput;
import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.service.AuthorService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @QueryMapping
    public Author authorById(@Argument Long id) {
        return authorService.findById(id);
    }

    @QueryMapping
    public List<Author> authors() {
        return authorService.findAll();
    }

    @MutationMapping
    public Author createAuthor(@Argument AuthorInput author) {
        return authorService.create(author);
    }

    @MutationMapping
    public Author updateAuthor(@Argument Long id, @Argument AuthorInput author) {
        return authorService.update(id, author);
    }

    @MutationMapping
    public boolean deleteAuthor(@Argument Long id) {
        return authorService.delete(id);
    }
}