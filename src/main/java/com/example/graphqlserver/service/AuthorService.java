package com.example.graphqlserver.service;

import com.example.graphqlserver.dto.AuthorInput;
import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author create(AuthorInput authorInput) {
        Author author = new Author();
        author.setName(authorInput.getName());
        return authorRepository.save(author);
    }

    public Author update(Long id, AuthorInput authorInput) {
        return authorRepository.findById(id)
                .map(author -> {
                    author.setName(authorInput.getName());
                    return authorRepository.save(author);
                })
                .orElse(null);
    }

    public boolean delete(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
