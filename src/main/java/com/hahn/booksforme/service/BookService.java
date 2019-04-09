package com.hahn.booksforme.service;

import java.util.Optional;

import com.hahn.booksforme.model.Author;
import com.hahn.booksforme.model.Book;
import com.hahn.booksforme.model.Genre;

public interface BookService {

	public Book save(Book book);

	public Optional<Book> findById(String id);

	public Iterable<Book> findByAuthor(Author author);

	public Iterable<Book> findByGenre(Genre genre);

	public void remove(Book book);
	
	public Long count();

}
