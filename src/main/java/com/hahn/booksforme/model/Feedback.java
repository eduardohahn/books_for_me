package com.hahn.booksforme.model;

public enum Feedback {

	LIKED("Liked the book"),
    DISLIKED("Disliked the book"),
    NOTINTERESTED("Not interested");
    
    private String description;

	Feedback(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }
}
