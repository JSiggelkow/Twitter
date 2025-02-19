package de.dhbw.twitterbackend.exceptions;

public class TweetNotFoundException extends RuntimeException {
	public TweetNotFoundException(Long tweetId) {
		super("Tweet with id: " + tweetId + " not found!");
	}
}
