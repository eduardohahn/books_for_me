package com.hahn.booksforme.service;

import java.util.Optional;

import com.hahn.booksforme.model.Author;

public interface AuthorService {

	public Author save(Author author);

	public Optional<Author> findByName(String name);

	public void remove(Author author);

}
