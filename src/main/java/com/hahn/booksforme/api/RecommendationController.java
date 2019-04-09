package com.hahn.booksforme.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hahn.booksforme.model.BookRecomendation;
import com.hahn.booksforme.model.User;
import com.hahn.booksforme.service.RecommendationService;
import com.hahn.booksforme.service.UserService;

@RestController
public class RecommendationController {

	@Autowired
	private UserService userService;

	@Autowired
	private RecommendationService recommendationService;

	@GetMapping(path = "/api/v1/recommendations/{username}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<String> getRecommendations(@PathVariable String username) {

		Optional<User> user = userService.getById(username);

		if (user.isPresent()) {

			BookRecomendation bookRecomendation = new BookRecomendation();
			bookRecomendation.setUsername(user.get().getUsername());
			bookRecomendation.setBooks(recommendationService.getRecommendations(user.get()));
			String json = "";

			ObjectMapper mapper = new ObjectMapper();
			try {
				json = mapper.writeValueAsString(bookRecomendation);

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			return ResponseEntity.status(HttpStatus.OK).body(json);

		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"message\" : \"User not found\" }");
		}
	}
}
