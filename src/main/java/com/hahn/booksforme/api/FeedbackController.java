package com.hahn.booksforme.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hahn.booksforme.model.Book;
import com.hahn.booksforme.model.FeedbackJson;
import com.hahn.booksforme.model.User;
import com.hahn.booksforme.model.UserFeedback;
import com.hahn.booksforme.service.BookService;
import com.hahn.booksforme.service.UserFeedbackService;
import com.hahn.booksforme.service.UserService;

@RestController
public class FeedbackController {

	@Autowired
	UserFeedbackService userFeedbackService;

	@Autowired
	UserService userService;

	@Autowired
	BookService bookService;

	@PostMapping(path = "/api/v1/feedback", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<String> insertFeedback(@RequestBody FeedbackJson userFeedback) {

		Optional<Book> book = bookService.findById(userFeedback.getBook());
		if (book.isPresent()) {

			Optional<User> user = userService.getById(userFeedback.getUsername());
			if (user.isPresent()) {

				Optional<UserFeedback> userFeedbackObject = userFeedbackService.findByUserAndBook(user.get(),
						book.get());
				if (userFeedbackObject.isPresent()) {
					userFeedbackObject.get().setFeedback(userFeedback.getFeedback());
					userFeedbackService.save(userFeedbackObject.get());
					return ResponseEntity.status(HttpStatus.OK).body("{ \"message\" : \"Feedback updated\" }");
				} else {
					UserFeedback newFeedback = new UserFeedback(user.get(), book.get(), userFeedback.getFeedback());
					userFeedbackService.save(newFeedback);
					return ResponseEntity.status(HttpStatus.CREATED).body("{ \"message\" : \"Feedback created\" }");
				}

			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"message\" : \"User not found\" }");
			}

		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"message\" : \"Book not found\" }");
		}

	}

	
}
