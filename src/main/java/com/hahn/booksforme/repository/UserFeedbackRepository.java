package com.hahn.booksforme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.hahn.booksforme.model.Book;
import com.hahn.booksforme.model.Feedback;
import com.hahn.booksforme.model.User;
import com.hahn.booksforme.model.UserFeedback;

public interface UserFeedbackRepository extends CrudRepository<UserFeedback, Integer> {

	@Query("select uf from UserFeedback uf where uf.user = :user")
	public Iterable<UserFeedback> findByUser(@Param("user") User user);
	
	@Query("select uf from UserFeedback uf where uf.user = :user and uf.book = :book")
	public Optional<UserFeedback> findByUserAndBook(@Param("user") User user, @Param("book") Book book);
	
	@Query("select uf from UserFeedback uf where uf.feedback = :feedback")
	public Iterable<UserFeedback> findByFeedback(@Param("feedback") Feedback feedback);
	
	
}
