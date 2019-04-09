package com.hahn.booksforme.service;

import java.util.Optional;

import com.hahn.booksforme.model.User;

public interface UserService {

	public void save(User user);

	public void remove(User user);

	public Optional<User> getById(String username);

	public Iterable<User> listUsers();

}
