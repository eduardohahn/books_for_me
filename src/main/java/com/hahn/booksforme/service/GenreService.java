package com.hahn.booksforme.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hahn.booksforme.model.Genre;

@Service
public interface GenreService {

	public Genre save(Genre genre);

	public Optional<Genre> findByName(String name);

	public void remove(Genre genre);

}
