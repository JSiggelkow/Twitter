package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.exceptions.PostAlreadyRepostedException;
import de.dhbw.twitterbackend.model.Retweet;
import de.dhbw.twitterbackend.model.RetweetId;
import de.dhbw.twitterbackend.model.Post;
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

	public void toggleRetweet(Post post, User user) {
		if (isPostRetweetedByUser(post, user)) {
			unRetweet(post, user);
		} else {
			retweet(post, user);
		}
	}

	private void retweet(Post post, User user) {
		if (isPostRetweetedByUser(post, user)) throw new PostAlreadyRepostedException();
		Retweet retweet = new Retweet();
		retweet.setId(new RetweetId(user.getId(), post.getId()));
		retweet.setPost(post);
		retweet.setUser(user);
		save(retweet);
	}

	private void unRetweet(Post post, User user) {
		retweetRepository.deleteById(new RetweetId(user.getId(), post.getId()));
	}

	public Long countByTweet(Post post) {
		return retweetRepository.countByPost(post);
	}

	public boolean isPostRetweetedByUser(Post post, User user) {
		return retweetRepository.existsById(new RetweetId(user.getId(), post.getId()));
	}

	public List<Retweet> getRetweetsBeforeRetweetedAtByLimit(int limit, OffsetDateTime timeLimit) {
		return retweetRepository.findByTweetCreatedAtOrderByTweetCreatedAtDesc(timeLimit, PageRequest.of(0, limit));
	}
}
