package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.dto.TweetDTO;
import de.dhbw.twitterbackend.mapper.TweetMapper;
import de.dhbw.twitterbackend.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {

	private final TweetService tweetService;
	private final RetweetService retweetService;
	private final TweetMapper tweetMapper;

	public List<TweetDTO> fetchByLimitAndAfterTime(int limit, OffsetDateTime timeLimit, UserPrincipal userPrincipal) {
		return mergeTweetsAndRetweets(limit, timeLimit, userPrincipal);
	}

	private List<TweetDTO> getTweetsByLimitAndTimeLimit(int limit, OffsetDateTime timeLimit, UserPrincipal userPrincipal) {
		return tweetService.getByLimitAndTimeLimit(limit, timeLimit).stream()
				.map(tweet -> tweetMapper.toDTO(tweet, userPrincipal)).toList();
	}

	private List<TweetDTO> getRetweetsByLimitAndTimeLimit(int limit, OffsetDateTime timeLimit, UserPrincipal userPrincipal) {
		return retweetService.getRetweetsBeforeRetweetedAtByLimit(limit, timeLimit).stream()
				.map(retweet -> tweetMapper.toDTO(userPrincipal, retweet))
				.toList();
	}

	private List<TweetDTO> mergeTweetsAndRetweets(int limit, OffsetDateTime timeLimit, UserPrincipal userPrincipal) {

		List<TweetDTO> feed = new ArrayList<>();
		feed.addAll(getTweetsByLimitAndTimeLimit(limit, timeLimit, userPrincipal));
		feed.addAll(timeFilteredRetweets(getRetweetsByLimitAndTimeLimit(limit, timeLimit, userPrincipal), feed));

		return feed.stream()
				.sorted(Comparator.comparing(TweetDTO::createdAt).reversed())
				.toList();
	}

	private List<TweetDTO> timeFilteredRetweets(List<TweetDTO> retweets, List<TweetDTO> tweets) {
		return retweets.stream()
				.filter(retweet -> retweet.createdAt().isAfter(findOldestTweetCreatedAt(tweets)))
				.toList();
	}

	private OffsetDateTime findOldestTweetCreatedAt(List<TweetDTO> tweets) {
		return tweets.stream()
				.map(TweetDTO::createdAt)
				.min(OffsetDateTime::compareTo)
				.orElse(OffsetDateTime.now());
	}
}
