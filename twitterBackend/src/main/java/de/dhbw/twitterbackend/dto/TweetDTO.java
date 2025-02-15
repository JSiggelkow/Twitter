package de.dhbw.twitterbackend.dto;

import de.dhbw.twitterbackend.model.Tweet;
import de.dhbw.twitterbackend.model.User;

import java.time.OffsetDateTime;

public record TweetDTO(Long id, String text, String video, String image, User userId, Tweet retweetID,
                       OffsetDateTime createdAt, Long countLikes, Long countRetweets, Long countComments) {
}
