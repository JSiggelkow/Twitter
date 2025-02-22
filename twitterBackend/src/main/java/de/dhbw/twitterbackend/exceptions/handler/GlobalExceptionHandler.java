package de.dhbw.twitterbackend.exceptions.handler;

import de.dhbw.twitterbackend.exceptions.TweetAlreadyRetweetedException;
import de.dhbw.twitterbackend.exceptions.TweetNotFoundException;
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

	@ExceptionHandler(TweetNotFoundException.class)
	public ResponseEntity<ErrorObject> handleTweetNotFoundException(TweetNotFoundException ex) {
		return new ResponseEntity<>(buildErrorObject(HttpStatus.NOT_FOUND.value(), ex), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(TweetAlreadyRetweetedException.class)
	public ResponseEntity<ErrorObject> handleTweetAlreadyRetweetedException(TweetAlreadyRetweetedException ex) {
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
