package de.dhbw.twitterbackend.exceptions.handler;

import de.dhbw.twitterbackend.exceptions.PostAlreadyRepostedException;
import de.dhbw.twitterbackend.exceptions.PostNotFoundException;
import de.dhbw.twitterbackend.exceptions.UserNotFoundException;
import de.dhbw.twitterbackend.util.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorObject> handleUserNotFoundException(UserNotFoundException ex) {
		return new ResponseEntity<>(buildErrorObject(HttpStatus.NOT_FOUND.value(), ex), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<ErrorObject> handleTweetNotFoundException(PostNotFoundException ex) {
		return new ResponseEntity<>(buildErrorObject(HttpStatus.NOT_FOUND.value(), ex), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PostAlreadyRepostedException.class)
	public ResponseEntity<ErrorObject> handleTweetAlreadyRetweetedException(PostAlreadyRepostedException ex) {
		return new ResponseEntity<>(buildErrorObject(HttpStatus.CONFLICT.value(), ex), HttpStatus.CONFLICT);
	}

	private ErrorObject buildErrorObject(int statusCode, RuntimeException ex) {

		ErrorObject errorObject = new ErrorObject();

		errorObject.setStatusCode(statusCode);
		errorObject.setTimestamp(ZonedDateTime.now());
		errorObject.setMessage(ex.getMessage());

		return errorObject;
	}
}
