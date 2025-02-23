package de.dhbw.twitterbackend.exceptions;

public class PostAlreadyRepostedException extends RuntimeException {
	public PostAlreadyRepostedException() {
		super("Tweet is already retweetet!");
	}
}
