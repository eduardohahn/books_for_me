package com.hahn.booksforme.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.hahn.booksforme.model.Author;
import com.hahn.booksforme.model.Book;
import com.hahn.booksforme.model.Genre;

public interface BookRepository extends CrudRepository<Book, String> {

	@Query("select b from Book b where b.author = :author")
	public Iterable<Book> findByAuthor(@Param("author") Author author);

	@Query("select b from Book b where b.genre = :genre")
	public Iterable<Book> findByGenre(@Param("genre") Genre genre);

}
