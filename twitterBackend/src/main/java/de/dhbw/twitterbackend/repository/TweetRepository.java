package de.dhbw.twitterbackend.repository;

import de.dhbw.twitterbackend.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
