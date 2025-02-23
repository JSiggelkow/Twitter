package de.dhbw.twitterbackend.exceptions;

public class PostNotFoundException extends RuntimeException {
	public PostNotFoundException(Long postId) {
		super("Tweet with id: " + postId + " not found!");
	}
}
