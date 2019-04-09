package com.hahn.booksforme.service;

import java.util.Optional;

import com.hahn.booksforme.model.Book;
import com.hahn.booksforme.model.Feedback;
import com.hahn.booksforme.model.User;
import com.hahn.booksforme.model.UserFeedback;

public interface UserFeedbackService {

	public UserFeedback save(UserFeedback userFeedback);

	public Iterable<UserFeedback> findByUser(User user);

	public Optional<UserFeedback> findByUserAndBook(User user, Book book);

	public Iterable<UserFeedback> findByFeedback(Feedback feedback);

	public void remove(UserFeedback userFeedback);

}
