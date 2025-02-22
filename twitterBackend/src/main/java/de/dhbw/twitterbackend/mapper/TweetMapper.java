package de.dhbw.twitterbackend.mapper;

import de.dhbw.twitterbackend.dto.TweetDTO;
import de.dhbw.twitterbackend.model.Tweet;
import de.dhbw.twitterbackend.security.UserPrincipal;
import de.dhbw.twitterbackend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TweetMapper {

	private final TweetLikeService tweetLikeService;
	private final RetweetService retweetService;
	private final CommentService commentService;
	private final UserService userService;

	public TweetDTO toDTO(Tweet tweet, UserPrincipal userPrincipal) {
		return new TweetDTO(tweet.getId(),
				tweet.getText(),
				tweet.getVideo(),
				tweet.getImage(),
				tweet.getUser().getUsername(),
				tweet.getRetweetId() != null ? tweet.getRetweetId().getId() : null,
				tweet.getCreatedAt(),
				tweetLikeService.countByTweet(tweet),
				retweetService.countByTweet(tweet),
				commentService.countByTweet(tweet),
				tweetLikeService.isTweetLikedByUser(tweet, userService.findByUsername(userPrincipal.getUsername())));
	}

}
