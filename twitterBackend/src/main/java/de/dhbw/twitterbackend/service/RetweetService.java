package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.exceptions.TweetAlreadyRetweetedException;
import de.dhbw.twitterbackend.model.*;
import de.dhbw.twitterbackend.repository.RetweetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetweetService {

	private final RetweetRepository retweetRepository;

	private void save(Retweet retweet) {
		retweetRepository.save(retweet);
	}

	public void retweet(Tweet tweet, User user) {
		if (isTweetRetweetedByUser(tweet, user)) throw new TweetAlreadyRetweetedException();
		Retweet retweet = new Retweet();
		retweet.setId(new RetweetId(user.getId(), tweet.getId()));
		retweet.setTweet(tweet);
		retweet.setUser(user);
		save(retweet);
	}

	public Long countByTweet(Tweet tweet) {
		return retweetRepository.countByTweet(tweet);
	}

	public boolean isTweetRetweetedByUser(Tweet tweet, User user) {
		return retweetRepository.existsById(new RetweetId(user.getId(), tweet.getId()));
	}

	public List<Retweet> getNewestByLimit(int limit) {
		return retweetRepository.findAllByOrderByRetweetedAtDesc(PageRequest.of(0, limit));
	}
}
