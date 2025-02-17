package de.dhbw.twitterbackend.exceptions.handler;

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
	public ResponseEntity<ErrorObject> handleUserNotFoundException() {
		return new ResponseEntity<>(buildErrorObject(HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
	}

	private ErrorObject buildErrorObject(int statusCode) {
		ErrorObject errorObject = new ErrorObject();

		errorObject.setStatusCode(statusCode);
		errorObject.setTimestamp(ZonedDateTime.now());
		
		return errorObject;
	}
}
