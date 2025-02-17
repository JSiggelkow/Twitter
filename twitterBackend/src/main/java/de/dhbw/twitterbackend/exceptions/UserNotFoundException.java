package de.dhbw.twitterbackend.exceptions;

import java.io.Serial;

public class UserNotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1;

	public UserNotFoundException(String username) {
		super("User with username: " + username + " not found!");
	}

	public UserNotFoundException(Long userId) {
		super("User with id: " + userId + " not found!");
	}
}
