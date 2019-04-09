package com.hahn.booksforme.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hahn.booksforme.model.Author;
import com.hahn.booksforme.model.User;
import com.hahn.booksforme.service.AuthorService;
import com.hahn.booksforme.service.UserService;

@RestController
public class MainController {

	@Autowired
	UserService userService;
	
	@Autowired
	AuthorService authorService;
	
	@GetMapping("/test")
	public String createUser() {

		Optional<User> user = userService.getById("eduardo");

		user.ifPresent(value -> {
			userService.remove(value);
		});

		return "OK";
	}

	@GetMapping("/author")
	public String testAuthor() {

		Optional<Author> author = authorService.findByName("eduardo");

		System.out.println("Has author: " + author.isPresent());
		
		author.ifPresent(value -> {
			System.out.println(value.toString());
		});

		return "OK";
	}
	
}
