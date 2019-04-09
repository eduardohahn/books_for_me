package com.hahn.booksforme.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class User {

	@Id
	@NotNull
	private String username;

	private String email;

	private String country;
	
	@OneToMany(mappedBy = "user")
	private Set<UserFeedback> feedbacks;

	protected User() {

	}

	public User(String username, String email, String country) {
		super();
		this.username = username;
		this.email = email;
		this.country = country;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
