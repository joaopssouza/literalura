package com.alura.literalura.repository;

import com.alura.literalura.model.Authors ;
import com.alura.literalura.model.Books ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooksRepository extends JpaRepository<Books, Long>{

    List<Books> findByLanguage(String language);

    Integer countByLanguage(String language);

    @Query("SELECT b.downloadNumbers FROM Books b")
    List<Integer> searchDownloadNumbers();

    @Query("SELECT a FROM Books b JOIN b.author a")
    List<Authors> searchAuthors();

    @Query("SELECT a FROM Books b JOIN b.author a WHERE a.birthYear <= :year and a.deathYear >= :year")
    List<Authors> searchLivingAuthors(Integer year);

    @Query("SELECT a FROM Books b JOIN b.author a WHERE a.name = :name")
    Authors findAuthorByName(String name);

    @Query("SELECT b FROM Books b WHERE b.language LIKE %:language%")
    List<Books> findBooksByLanguages(String language);
}
