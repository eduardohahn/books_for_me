package com.hahn.booksforme.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hahn.booksforme.model.Author;
import com.hahn.booksforme.model.Book;
import com.hahn.booksforme.model.Genre;
import com.hahn.booksforme.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book save(Book book) {

		return bookRepository.save(book);

	}

	@Override
	public Optional<Book> findById(String id) {
		return bookRepository.findById(id);
	}

	@Override
	public Iterable<Book> findByAuthor(Author author) {

		return bookRepository.findByAuthor(author);
	}

	@Override
	public Iterable<Book> findByGenre(Genre genre) {

		return bookRepository.findByGenre(genre);
	}

	@Override
	public void remove(Book book) {

		bookRepository.delete(book);

	}

	@Override
	public Long count() {
		
		return bookRepository.count();
	}

}
