package com.example.graphqlserver;

import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.model.Book;
import com.example.graphqlserver.repository.AuthorRepository;
import com.example.graphqlserver.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(AuthorRepository authorRepository, BookRepository bookRepository) {
        return args -> {
            // Create authors
            Author author1 = new Author();
            author1.setName("J.K. Rowling");
            authorRepository.save(author1);

            Author author2 = new Author();
            author2.setName("George Orwell");
            authorRepository.save(author2);

            // Create books
            Book book1 = new Book();
            book1.setTitle("Harry Potter and the Philosopher's Stone");
            book1.setPageCount(223);
            book1.setAuthor(author1);
            bookRepository.save(book1);

            Book book2 = new Book();
            book2.setTitle("1984");
            book2.setPageCount(328);
            book2.setAuthor(author2);
            bookRepository.save(book2);
        };
    }
}