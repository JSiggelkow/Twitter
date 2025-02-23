package de.dhbw.twitterbackend.dto;

import java.time.OffsetDateTime;

public record QuoteDTO(Long postId, String text, String username, OffsetDateTime createdAt) {
}
