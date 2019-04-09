package com.hahn.booksforme.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hahn.booksforme.model.Author;
import com.hahn.booksforme.model.Book;
import com.hahn.booksforme.model.Feedback;
import com.hahn.booksforme.model.Genre;
import com.hahn.booksforme.model.User;
import com.hahn.booksforme.model.UserFeedback;

@Service
public class RecommendationService {

	@Autowired
	UserFeedbackService userFeedbackService;

	@Autowired
	BookService bookService;

	public Set<Book> getRecommendations(User user) {

		Iterable<UserFeedback> userFeedbacks = userFeedbackService.findByUser(user);

		if (StreamSupport.stream(userFeedbacks.spliterator(), false).count() > 0) {

			Set<Author> authorsWithFeedback = new HashSet<>();
			Set<Author> authorsWithBadFeedback = new HashSet<>();
			Set<Genre> genresWithFeedback = new HashSet<>();
			Set<Genre> genresWithBadFeedback = new HashSet<>();
			Set<Book> booksWithFeedback = new HashSet<>();
			Set<Book> booksWithBadFeedback = new HashSet<>();
			Set<Book> recommendations = new HashSet<>();

			for (UserFeedback feedback : userFeedbacks) {

				if (feedback.getFeedback().equals(Feedback.LIKED)) {
					booksWithFeedback.add(feedback.getBook());
					genresWithFeedback.add(feedback.getBook().getGenre());
					authorsWithFeedback.add(feedback.getBook().getAuthor());
				} else {
					booksWithBadFeedback.add(feedback.getBook());
					genresWithBadFeedback.add(feedback.getBook().getGenre());
					authorsWithBadFeedback.add(feedback.getBook().getAuthor());
				}

			}

			for (Genre genre : genresWithFeedback) {
				Iterable<Book> booksByGenre = bookService.findByGenre(genre);

				for (Book book : booksByGenre) {
					if (!genresWithBadFeedback.contains(book.getGenre())
							&& !authorsWithBadFeedback.contains(book.getAuthor())
							&& !booksWithBadFeedback.contains(book) && !booksWithFeedback.contains(book)) {

						recommendations.add(book);

					}

				}
			}

			for (Author author : authorsWithFeedback) {
				Iterable<Book> booksByAuthor = bookService.findByAuthor(author);

				for (Book book : booksByAuthor) {
					if (!genresWithBadFeedback.contains(book.getGenre())
							&& !authorsWithBadFeedback.contains(book.getAuthor())
							&& !booksWithBadFeedback.contains(book) && !booksWithFeedback.contains(book)) {

						recommendations.add(book);

					}
				}
			}

			if (recommendations.size() > 20) {
				return reduceToTwenty(recommendations);
			} else {
				if (recommendations.size() == 0) {
					return getBestBooks(user);
				} else {
					return recommendations;
				}

			}

		} else {
			System.out.println("User don't have feedbacks, returning best books.");
			return getBestBooks(user);
		}

	}

	public Set<Book> getBestBooks(User user) {

		Map<Book, Integer> map = new HashMap<Book, Integer>();
		Set<Book> books = new HashSet<>();

		Iterable<UserFeedback> bestBooks = userFeedbackService.findByFeedback(Feedback.LIKED);

		for (UserFeedback userFeedback : bestBooks) {

			if (!userFeedback.getUser().equals(user)) {
				if (map.containsKey(userFeedback.getBook())) {

					map.replace(userFeedback.getBook(), map.get(userFeedback.getBook()),
							map.get(userFeedback.getBook()).intValue() + 1);
				} else {
					map.put(userFeedback.getBook(), 1);
				}
			}

		}

		Map<Book, Integer> sorted = map.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

		for (Map.Entry<Book, Integer> entry : map.entrySet()) {
			books.add(entry.getKey());
		}

		if (books.size() > 20) {
			return reduceToTwenty(books);
		} else {
			return books;
		}

	}

	private Set<Book> reduceToTwenty(Set<Book> books) {

		int maxRecommendations = 21;

		for (Iterator<Book> iterator = books.iterator(); iterator.hasNext();) {

			Book book = iterator.next();
			if (maxRecommendations == 0) {
				iterator.remove();
			} else {
				maxRecommendations--;
			}

		}

		return books;
	}

}
