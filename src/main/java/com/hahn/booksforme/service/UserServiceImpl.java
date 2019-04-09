package com.hahn.booksforme.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hahn.booksforme.model.User;
import com.hahn.booksforme.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public void save(User user) {
		userRepository.save(user);

	}

	@Override
	public Optional<User> getById(String username) {

		return userRepository.findById(username);
	}

	@Override
	public Iterable<User> listUsers() {

		return userRepository.findAll();
	}

	@Override
	public void remove(User user) {

		userRepository.delete(user);

	}

}
