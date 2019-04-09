package com.hahn.booksforme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.hahn.booksforme.model.Genre;

public interface GenreRepository extends CrudRepository<Genre, Integer> {

	@Query("select g from Genre g where g.name = :name")
	public Optional<Genre> findByName(@Param("name") String name);
}
