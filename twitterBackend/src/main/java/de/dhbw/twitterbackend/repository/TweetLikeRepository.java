package de.dhbw.twitterbackend.repository;

import de.dhbw.twitterbackend.model.Tweet;
import de.dhbw.twitterbackend.model.TweetLike;
import de.dhbw.twitterbackend.model.TweetLikeId;
import de.dhbw.twitterbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetLikeRepository extends JpaRepository<TweetLike, TweetLikeId> {

	Long countByTweet(Tweet tweet);

	boolean existsTweetLikeByTweetAndUser(Tweet tweet, User user);
}
