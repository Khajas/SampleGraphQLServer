package com.example.graphqlserver.controller;

import com.example.graphqlserver.dto.BookInput;
import com.example.graphqlserver.model.Book;
import com.example.graphqlserver.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public Book bookById(@Argument Long id) {
        return bookService.findById(id);
    }

    @QueryMapping
    public List<Book> books() {
        return bookService.findAll();
    }

    @MutationMapping
    public Book createBook(@Argument BookInput book) {
        return bookService.create(book);
    }

    @MutationMapping
    public Book updateBook(@Argument Long id, @Argument BookInput book) {
        return bookService.update(id, book);
    }

    @MutationMapping
    public boolean deleteBook(@Argument Long id) {
        return bookService.delete(id);
    }
}
