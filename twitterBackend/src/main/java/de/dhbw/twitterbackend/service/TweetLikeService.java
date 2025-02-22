package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.model.Tweet;
import de.dhbw.twitterbackend.model.TweetLike;
import de.dhbw.twitterbackend.model.TweetLikeId;
import de.dhbw.twitterbackend.model.User;
import de.dhbw.twitterbackend.repository.TweetLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TweetLikeService {

	private final TweetLikeRepository tweetLikeRepository;

	public TweetLike save(TweetLike tweetLike) {
		return tweetLikeRepository.save(tweetLike);
	}

	public void likeTweet(Tweet tweet, User user) {
		TweetLike tweetLike = new TweetLike();
		tweetLike.setId(new TweetLikeId(user.getId(), tweet.getId()));
		tweetLike.setTweet(tweet);
		tweetLike.setUser(user);
		save(tweetLike);
	}

	public Long countByTweet(Tweet tweet) {
		return tweetLikeRepository.countByTweet(tweet);
	}

	public boolean isTweetLikedByUser(Tweet tweet, User user) {
		return tweetLikeRepository.existsTweetLikeByTweetAndUser(tweet, user);
	}
}
