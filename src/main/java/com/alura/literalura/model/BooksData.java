package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BooksData(@JsonAlias("title") String title, @JsonAlias("authors") List<AuthorsData> authors,
                        @JsonAlias("languages") List<String> languages,
                        @JsonAlias("download_count") Integer downloadsNumber) {

}
