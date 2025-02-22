package de.dhbw.twitterbackend.dto;

import java.time.OffsetDateTime;

public record QuoteDTO(Long tweetID, String text, String username, OffsetDateTime createdAt) {
}
