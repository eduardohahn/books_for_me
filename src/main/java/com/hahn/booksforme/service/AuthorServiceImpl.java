package com.hahn.booksforme.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hahn.booksforme.model.Author;
import com.hahn.booksforme.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public Author save(Author author) {

		return authorRepository.save(author);

	}

	@Override
	public Optional<Author> findByName(String name) {

		return authorRepository.findByName(name);
	}

	@Override
	public void remove(Author author) {
		authorRepository.delete(author);

	}

}
