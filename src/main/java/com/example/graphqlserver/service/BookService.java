package com.example.graphqlserver.service;

import com.example.graphqlserver.dto.BookInput;
import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.model.Book;
import com.example.graphqlserver.repository.AuthorRepository;
import com.example.graphqlserver.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book create(BookInput bookInput) {
        Book book = new Book();
        book.setTitle(bookInput.getTitle());
        book.setPageCount(bookInput.getPageCount());

        if (bookInput.getAuthorId() != null) {
            Author author = authorRepository.findById(bookInput.getAuthorId()).orElse(null);
            book.setAuthor(author);
        }

        return bookRepository.save(book);
    }

    public Book update(Long id, BookInput bookInput) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(bookInput.getTitle());
                    book.setPageCount(bookInput.getPageCount());

                    if (bookInput.getAuthorId() != null) {
                        Author author = authorRepository.findById(bookInput.getAuthorId()).orElse(null);
                        book.setAuthor(author);
                    }

                    return bookRepository.save(book);
                })
                .orElse(null);
    }

    public boolean delete(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
