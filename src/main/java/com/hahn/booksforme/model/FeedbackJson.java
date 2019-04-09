package com.hahn.booksforme.model;

public class FeedbackJson {

	private String username;

	private String book;

	private Feedback feedback;

	public FeedbackJson() {

	}

	public FeedbackJson(String username, String book, Feedback feedback) {
		super();
		this.username = username;
		this.book = book;
		this.feedback = feedback;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	};

}
