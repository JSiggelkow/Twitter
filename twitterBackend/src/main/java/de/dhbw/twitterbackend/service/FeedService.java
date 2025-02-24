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

/**
 * Service responsible for providing and managing the user's feed, which consists of
 * both tweets and retweets. The FeedService interacts with the PostService and
 * RetweetService to fetch posts and retweets and merges them to create a complete
 * feed for the user.
 */
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

	/**
	 * Combines tweets and retweets for a user's feed, sorts them in descending
	 * order by creation time, and applies a limit and time restriction.
	 *
	 * @param limit the maximum number of tweets and retweets to return
	 * @param timeLimit the time limit for filtering tweets and retweets
	 * @param userPrincipal the user principal containing user-specific information
	 * @return a combined and sorted list of tweet and retweet DTOs
	 */
	private List<PostDTO> mergeTweetsAndRetweets(int limit, OffsetDateTime timeLimit, UserPrincipal userPrincipal) {

		List<PostDTO> feed = new ArrayList<>();
		feed.addAll(getTweetsByLimitAndTimeLimit(limit, timeLimit, userPrincipal));
		feed.addAll(timeFilteredRetweets(getRetweetsByLimitAndTimeLimit(limit, timeLimit, userPrincipal), feed));

		return feed.stream()
				.sorted(Comparator.comparing(PostDTO::createdAt).reversed())
				.toList();
	}

	/**
	 * The following method filters retweets to include only those created
	 * after the oldest tweet in the fetched feed.
	 *
	 */

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
