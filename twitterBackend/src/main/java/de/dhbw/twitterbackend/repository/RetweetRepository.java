package de.dhbw.twitterbackend.repository;

import de.dhbw.twitterbackend.model.Retweet;
import de.dhbw.twitterbackend.model.RetweetId;
import de.dhbw.twitterbackend.model.Tweet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RetweetRepository extends JpaRepository<Retweet, RetweetId> {

	Long countByTweet(Tweet tweet);

	List<Retweet> findAllByOrderByRetweetedAtDesc(Pageable pageable);
}
