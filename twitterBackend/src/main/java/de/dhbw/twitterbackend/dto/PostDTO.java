package de.dhbw.twitterbackend.dto;

import java.time.OffsetDateTime;

public record PostDTO(Long id,
                      String text,
                      String video,
                      String image,
                      String username,
                      String retweetedByUsername,
                      QuoteDTO quoteDTO,
                      OffsetDateTime createdAt,
                      Long countLikes,
                      Long countRetweets,
                      Long countComments,
                      boolean isLiked,
                      boolean isRetweeted) {
}
