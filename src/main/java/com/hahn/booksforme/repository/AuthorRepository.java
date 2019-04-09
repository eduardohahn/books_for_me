package com.hahn.booksforme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.hahn.booksforme.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

	@Query("select a from Author a where a.name = :name")
	public Optional<Author> findByName(@Param("name") String name);
}
