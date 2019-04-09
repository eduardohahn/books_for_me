package com.hahn.booksforme.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hahn.booksforme.model.Genre;
import com.hahn.booksforme.repository.GenreRepository;

@Service
public class GenreServiceImpl implements GenreService {

	@Autowired
	private GenreRepository genreRepository;

	@Override
	public Genre save(Genre genre) {

		return genreRepository.save(genre);

	}

	@Override
	public Optional<Genre> findByName(String name) {

		return genreRepository.findByName(name);
	}

	@Override
	public void remove(Genre genre) {

		genreRepository.delete(genre);

	}

}
