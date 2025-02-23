package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.dto.PostDTO;
import de.dhbw.twitterbackend.mapper.PostMapper;
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

	private final PostService postService;
	private final RetweetService retweetService;
	private final PostMapper postMapper;

	public List<PostDTO> fetchByLimitAndAfterTime(int limit, OffsetDateTime timeLimit, UserPrincipal userPrincipal) {
		return mergeTweetsAndRetweets(limit, timeLimit, userPrincipal);
	}

	private List<PostDTO> getTweetsByLimitAndTimeLimit(int limit, OffsetDateTime timeLimit, UserPrincipal userPrincipal) {
		return postService.getByLimitAndTimeLimit(limit, timeLimit).stream()
				.map(tweet -> postMapper.toDTO(tweet, userPrincipal)).toList();
	}

	private List<PostDTO> getRetweetsByLimitAndTimeLimit(int limit, OffsetDateTime timeLimit, UserPrincipal userPrincipal) {
		return retweetService.getRetweetsBeforeRetweetedAtByLimit(limit, timeLimit).stream()
				.map(retweet -> postMapper.toDTO(userPrincipal, retweet))
				.toList();
	}

	private List<PostDTO> mergeTweetsAndRetweets(int limit, OffsetDateTime timeLimit, UserPrincipal userPrincipal) {

		List<PostDTO> feed = new ArrayList<>();
		feed.addAll(getTweetsByLimitAndTimeLimit(limit, timeLimit, userPrincipal));
		feed.addAll(timeFilteredRetweets(getRetweetsByLimitAndTimeLimit(limit, timeLimit, userPrincipal), feed));

		return feed.stream()
				.sorted(Comparator.comparing(PostDTO::createdAt).reversed())
				.toList();
	}

	private List<PostDTO> timeFilteredRetweets(List<PostDTO> retweets, List<PostDTO> tweets) {
		return retweets.stream()
				.filter(retweet -> retweet.createdAt().isAfter(findOldestTweetCreatedAt(tweets)))
				.toList();
	}

	private OffsetDateTime findOldestTweetCreatedAt(List<PostDTO> tweets) {
		return tweets.stream()
				.map(PostDTO::createdAt)
				.min(OffsetDateTime::compareTo)
				.orElse(OffsetDateTime.now());
	}
}
