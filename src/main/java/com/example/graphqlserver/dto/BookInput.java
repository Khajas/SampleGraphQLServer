package com.example.graphqlserver.dto;

import lombok.Data;

@Data
public class BookInput {
    private String title;
    private Integer pageCount;
    private Long authorId;
}