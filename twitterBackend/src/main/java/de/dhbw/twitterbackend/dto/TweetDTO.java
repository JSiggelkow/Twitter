package de.dhbw.twitterbackend.dto;

import java.time.OffsetDateTime;

public record TweetDTO(Long id,
                       String text,
                       String video,
                       String image,
                       String username,
					   String retweetedByUsername,
                       Long retweetID,
                       OffsetDateTime createdAt,
                       Long countLikes,
                       Long countRetweets,
                       Long countComments,
                       boolean isLiked,
                       boolean isRetweeted) {
}
