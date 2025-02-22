package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.dto.TweetDTO;
import de.dhbw.twitterbackend.mapper.TweetMapper;
import de.dhbw.twitterbackend.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {

	private final TweetService tweetService;
	private final RetweetService retweetService;
	private final TweetMapper tweetMapper;

	public List<TweetDTO> fetchNewestByLimit(int limit, UserPrincipal userPrincipal) {
		return mergeTweetsAndRetweets(limit, userPrincipal);
	}

	private List<TweetDTO> getNewestTweetsByLimit(int limit, UserPrincipal userPrincipal) {
		return tweetService.getNewestByLimit(limit).stream()
				.map(tweet -> tweetMapper.toDTO(tweet, userPrincipal)).toList();
	}

	private List<TweetDTO> getNewestRetweetsByLimit(int limit, UserPrincipal userPrincipal) {
		return retweetService.getNewestByLimit(limit).stream()
				.filter(retweet -> retweet.getUser().getId() != userPrincipal.getUserId())
				.map(retweet -> tweetMapper.toDTO(userPrincipal, retweet))
				.toList();
	}

	private List<TweetDTO> mergeTweetsAndRetweets(int limit, UserPrincipal userPrincipal) {

		List<TweetDTO> feed = new ArrayList<>();
		feed.addAll(getNewestTweetsByLimit(limit, userPrincipal));
		feed.addAll(getNewestRetweetsByLimit(5, userPrincipal)); //hardcoded retweet limit of 5 --> only because this is a prototype

		return feed.stream()
				.sorted(Comparator.comparing(TweetDTO::createdAt).reversed())
				.toList();


	}
}
