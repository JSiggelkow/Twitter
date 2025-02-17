package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.model.Tweet;
import de.dhbw.twitterbackend.repository.TweetRepository;
import de.dhbw.twitterbackend.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TweetService {

	private final TweetRepository tweetRepository;
	private final UserService userService;

	public Tweet save(Tweet tweet) {
		return tweetRepository.save(tweet);
	}

	public Optional<Tweet> findById(Long id) {
		return tweetRepository.findById(id);
	}

	public List<Tweet> findAll() {
		return tweetRepository.findAll();
	}

	public Tweet postTweet(Tweet tweet, UserPrincipal userPrincipal) {
		tweet.setUser(userService.findByUsername(userPrincipal.getUsername()));

		return save(tweet);
	}
}
