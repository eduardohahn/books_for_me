package com.hahn.booksforme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book {

	@Id
	@Column(name = "id")
	private String asin;

	private String title;

	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private Author author;

	@ManyToOne
	@JoinColumn(name = "genre_id", nullable = false)
	private Genre genre;

	public Book() {

	}

	public Book(String asin, String title, Author author, Genre genre) {
		super();
		this.asin = asin;
		this.title = title;
		this.author = author;
		this.genre = genre;
	}

	public String getAsin() {
		return asin;
	}

	public void setAsin(String asin) {
		this.asin = asin;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	};

}
