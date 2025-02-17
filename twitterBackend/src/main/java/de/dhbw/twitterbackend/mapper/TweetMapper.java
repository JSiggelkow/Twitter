package de.dhbw.twitterbackend.mapper;

import de.dhbw.twitterbackend.dto.TweetDTO;
import de.dhbw.twitterbackend.model.Tweet;
import de.dhbw.twitterbackend.service.CommentService;
import de.dhbw.twitterbackend.service.RetweetService;
import de.dhbw.twitterbackend.service.TweetLikeService;
import de.dhbw.twitterbackend.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TweetMapper {

	private final TweetLikeService tweetLikeService;
	private final RetweetService retweetService;
	private final CommentService commentService;
	private final TweetService tweetService;

	public TweetDTO toDTO(Tweet tweet) {
		return new TweetDTO(tweet.getId(),
				tweet.getText(),
				tweet.getVideo(),
				tweet.getImage(),
				tweet.getUser().getUsername(),
				tweet.getRetweetId() != null ? tweet.getRetweetId().getId() : null,
				tweet.getCreatedAt(),
				tweetLikeService.countByTweet(tweet),
				retweetService.countByTweet(tweet),
				commentService.countByTweet(tweet));
	}

	public Tweet toTweet(TweetDTO tweetDTO) {
		return tweetService.findById(tweetDTO.id());
	}
}
