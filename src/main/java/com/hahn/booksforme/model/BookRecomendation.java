package com.hahn.booksforme.model;

import java.util.Set;

public class BookRecomendation {

	private String username;

	private Set<Book> books;

	public BookRecomendation() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	};

}
