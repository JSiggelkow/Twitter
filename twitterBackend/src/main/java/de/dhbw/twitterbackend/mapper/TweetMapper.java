package de.dhbw.twitterbackend.mapper;

import de.dhbw.twitterbackend.dto.CreateTweetDTO;
import de.dhbw.twitterbackend.dto.TweetDTO;
import de.dhbw.twitterbackend.model.Retweet;
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
	private final QuoteMapper quoteMapper;
	private final TweetService tweetService;

	public TweetDTO toDTO(Tweet tweet, UserPrincipal userPrincipal) {
		return new TweetDTO(tweet.getId(),
				tweet.getText(),
				tweet.getVideo(),
				tweet.getImage(),
				tweet.getUser().getUsername(),
				null,
				tweet.getRetweetId() != null ? quoteMapper.toDTO(tweet.getRetweetId()) : null,
				tweet.getCreatedAt(),
				tweetLikeService.countByTweet(tweet),
				retweetService.countByTweet(tweet),
				commentService.countByTweet(tweet),
				tweetLikeService.isTweetLikedByUser(tweet, userService.findByUsername(userPrincipal.getUsername())),
				retweetService.isTweetRetweetedByUser(tweet, userService.findByUsername(userPrincipal.getUsername())));
	}

	/**
	 * overloaded toDTO methods for retweets without text
	 */
	public TweetDTO toDTO(UserPrincipal userPrincipal, Retweet retweet) {
		return new TweetDTO(retweet.getTweet().getId(),
				retweet.getTweet().getText(),
				retweet.getTweet().getVideo(),
				retweet.getTweet().getImage(),
				retweet.getTweet().getUser().getUsername(),
				retweet.getUser().getUsername(),
				retweet.getTweet().getRetweetId() != null ? quoteMapper.toDTO(retweet.getTweet().getRetweetId()) : null,
				retweet.getTweet().getCreatedAt(),
				tweetLikeService.countByTweet(retweet.getTweet()),
				retweetService.countByTweet(retweet.getTweet()),
				commentService.countByTweet(retweet.getTweet()),
				tweetLikeService.isTweetLikedByUser(retweet.getTweet(), userService.findByUsername(userPrincipal.getUsername())),
				retweet.getUser().getUsername().equals(userPrincipal.getUsername()));
	}

	public Tweet toTweet(CreateTweetDTO createTweetDTO, UserPrincipal userPrincipal) {
		Tweet tweet = new Tweet();
		tweet.setText(createTweetDTO.text());
		tweet.setUser(userService.findByUsername(userPrincipal.getUsername()));
		if (createTweetDTO.retweetId() != null) {
			tweet.setRetweetId(tweetService.findById(createTweetDTO.retweetId()));
		}
		return tweet;
	}
}
