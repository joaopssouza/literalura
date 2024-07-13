package com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @ManyToOne(cascade = CascadeType.ALL)
    private Authors author;
    private String language;
    private Integer downloadNumbers;

    public Books() {}

    public Books(BooksData booksData) {
        this.title = booksData.title();
        Authors authors = new Authors(booksData.authors().get(0));
        this.author = authors;
        this.language = booksData.languages().get(0);
        this.downloadNumbers = booksData.downloadsNumber();
    }

    public Books(Long id, String title, Authors authors, String language, Integer downloadNumbers) {
        this.title = title;
        this.author = authors;
        this.language = language;
        this.downloadNumbers = downloadNumbers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Authors getAuthor() {
        return author;
    }

    public void setAuthor(Authors author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getDownloadNumbers() {
        return downloadNumbers;
    }

    public void setDownloadNumbers(Integer downloadNumbers) {
        this.downloadNumbers = downloadNumbers;
    }

    @Override
    public String toString() {
        return "------------------ Livro ------------------" +
                "\nTitulo: " + title +
                "\nAutor: " + author.getName() +
                "\nLinguagem: " + language +
                "\nNúmero de Downloads: " + downloadNumbers +
                "\n-------------------------------------------\n";
    }
}
