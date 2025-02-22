package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.exceptions.TweetAlreadyRetweetedException;
import de.dhbw.twitterbackend.model.Retweet;
import de.dhbw.twitterbackend.model.RetweetId;
import de.dhbw.twitterbackend.model.Tweet;
import de.dhbw.twitterbackend.model.User;
import de.dhbw.twitterbackend.repository.RetweetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RetweetService {

	private final RetweetRepository retweetRepository;

	private void save(Retweet retweet) {
		retweetRepository.save(retweet);
	}

	public void toggleRetweet(Tweet tweet, User user) {
		if (isTweetRetweetedByUser(tweet, user)) {
			unRetweet(tweet, user);
		} else {
			retweet(tweet, user);
		}
	}

	private void retweet(Tweet tweet, User user) {
		if (isTweetRetweetedByUser(tweet, user)) throw new TweetAlreadyRetweetedException();
		Retweet retweet = new Retweet();
		retweet.setId(new RetweetId(user.getId(), tweet.getId()));
		retweet.setTweet(tweet);
		retweet.setUser(user);
		save(retweet);
	}

	private void unRetweet(Tweet tweet, User user) {
		retweetRepository.deleteById(new RetweetId(user.getId(), tweet.getId()));
	}

	public Long countByTweet(Tweet tweet) {
		return retweetRepository.countByTweet(tweet);
	}

	private boolean isTweetRetweetedByUser(Tweet tweet, User user) {
		return retweetRepository.existsById(new RetweetId(user.getId(), tweet.getId()));
	}

	public List<Retweet> getRetweetsBeforeRetweetedAtByLimit(int limit, OffsetDateTime timeLimit) {
		return retweetRepository.findByTweetCreatedAtOrderByTweetCreatedAtDesc(timeLimit, PageRequest.of(0, limit));
	}
}
