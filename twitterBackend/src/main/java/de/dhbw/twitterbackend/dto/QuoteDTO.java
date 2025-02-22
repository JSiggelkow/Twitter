package de.dhbw.twitterbackend.dto;

import java.time.OffsetDateTime;

public record QuoteDTO(Long tweetID, String message, String username, OffsetDateTime createdAt) {
}
