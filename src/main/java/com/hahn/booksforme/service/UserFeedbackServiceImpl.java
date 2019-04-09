package com.hahn.booksforme.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hahn.booksforme.model.Book;
import com.hahn.booksforme.model.Feedback;
import com.hahn.booksforme.model.User;
import com.hahn.booksforme.model.UserFeedback;
import com.hahn.booksforme.repository.UserFeedbackRepository;

@Service
public class UserFeedbackServiceImpl implements UserFeedbackService {

	@Autowired
	UserFeedbackRepository userFeedbackRepository;

	@Override
	public UserFeedback save(UserFeedback userFeedback) {

		return userFeedbackRepository.save(userFeedback);

	}

	@Override
	public Optional<UserFeedback> findByUserAndBook(User user, Book book) {

		return userFeedbackRepository.findByUserAndBook(user, book);
	}

	@Override
	public Iterable<UserFeedback> findByUser(User user) {

		return userFeedbackRepository.findByUser(user);
	}

	@Override
	public Iterable<UserFeedback> findByFeedback(Feedback feedback) {

		return userFeedbackRepository.findByFeedback(feedback);
	}

	@Override
	public void remove(UserFeedback userFeedback) {
		userFeedbackRepository.delete(userFeedback);

	}

}
