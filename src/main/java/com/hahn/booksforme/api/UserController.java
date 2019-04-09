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

import com.hahn.booksforme.model.User;
import com.hahn.booksforme.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(path = "/api/v1/user", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<String> createUser(@RequestBody User user) {

		String message = "";

		Optional<User> userFound = userService.getById(user.getUsername());

		if (userFound.isPresent()) {
			message = "{ \"message\" : \"username already exists\" }";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
		} else {
			message = "{ \"message\" : \"user created\" }";
			userService.save(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(message);
		}

	}

}
