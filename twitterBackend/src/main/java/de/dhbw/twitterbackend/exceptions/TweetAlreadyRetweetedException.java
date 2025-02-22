package de.dhbw.twitterbackend.exceptions;

public class TweetAlreadyRetweetedException extends RuntimeException {
	public TweetAlreadyRetweetedException() {
		super("Tweet is already retweetet!");
	}
}
