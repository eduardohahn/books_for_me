package com.hahn.booksforme.repository;

import org.springframework.data.repository.CrudRepository;

import com.hahn.booksforme.model.User;

public interface UserRepository extends CrudRepository<User, String> {

}
