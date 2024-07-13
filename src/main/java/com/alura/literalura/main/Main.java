package com.alura.literalura.main;

import com.alura.literalura.model.Authors ;
import com.alura.literalura.model.Books ;
import com.alura.literalura.model.BooksData ;
import com.alura.literalura.repository.BooksRepository ;
import com.alura.literalura.service.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final BooksRepository repository;
    private final String API_URL = "https://gutendex.com/books/?search=";

    private Scanner reading = new Scanner(System.in);
    private ConsumeAPI consumeAPI = new ConsumeAPI();
    private DataConversion dataConversion = new DataConversion();
    private LanguageConversion languageConversion = new LanguageConversion();

    public Main(BooksRepository repository) {
        this.repository = repository;
    }

    public void showMenu() {
        var option = -1;

        while (option != 0) {
            var menu = """
                    *** API de Pesquisa de Livros ***
                                        
                    1- Pesquisar livros por título
                    2- Listar livros registrados
                    3- Listar autores registrados
                    4- Liste autores vivos em um determinado ano
                    5- Listar livros em um determinado idioma
                                    
                    0 - Sair
                    """;

            System.out.println(menu);
            option = reading.nextInt();
            reading.nextLine();

            switch (option) {
                case 1:
                    searchBooksByTitle();
                    break;
                case 2:
                    listRegisteredBooks();
                    break;
                case 3:
                    listRegisteredAuthors();
                    break;
                case 4:
                    listLivingAuthorsInYear();
                    break;
                case 5:
                    listBooksInCertainLanguage();
                    break;
                case 0:
                    System.out.println("Saindo...!");
                    break;
                default:
                    System.out.println("Entrada inválida!");
            }
        }
    }

    private void searchBooksByTitle() {
        System.out.println("Insira um título de livro:");
        var bookName = reading.nextLine();
        String searchUrl = API_URL.concat(bookName.replace(" ", "+").toLowerCase().trim());

        String json = consumeAPI.getData(searchUrl);
        String jsonBook = dataConversion.extractObjectFromJson(json, "Resultados");

        List<BooksData> booksDTO = dataConversion.getList(jsonBook, BooksData.class);

        if (booksDTO.size() > 0) {
            Books books = new Books(booksDTO.get(0));

            Authors author = repository.findAuthorByName(books.getAuthor().getName());
            if (author != null) {
                books.setAuthor(null);
                repository.save(books);
                books.setAuthor(author);
            }
            books = repository.save(books);
            System.out.println(books);
        } else {
            System.out.println("Livro não encontrado!");
        }
    }

    private void listRegisteredBooks() {
        List<Books> books = repository.findAll();
        books.forEach(System.out::println);
    }

    private void listRegisteredAuthors() {
        List<Authors> authors = repository.searchAuthors();
        authors.forEach(System.out::println);
    }

    private void listLivingAuthorsInYear() {
        try {
            System.out.println("Digite um ano:");
            var year = reading.nextInt();
            reading.nextLine();

            List<Authors> authors = repository.searchLivingAuthors(year);
            authors.forEach(System.out::println);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Insira um número inteiro.");
            reading.nextLine();
        }
    }

    private void listBooksInCertainLanguage() {
        System.out.println("Insira um idioma: ");
        var language = languageConversion.convertLanguage(reading.nextLine());
        List<Books> books = repository.findBooksByLanguages(language);
        if (!books.isEmpty()) {
            books.forEach(System.out::println);
        } else {
            System.out.printf("Não há livros no %s linguagem %n", language);
        }
    }
}
