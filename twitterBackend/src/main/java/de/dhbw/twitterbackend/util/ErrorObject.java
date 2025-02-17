package de.dhbw.twitterbackend.util;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ErrorObject {
	private int statusCode;
	private String message;
	private ZonedDateTime timestamp;
}
