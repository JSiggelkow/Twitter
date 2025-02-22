package de.dhbw.twitterbackend.repository;

import de.dhbw.twitterbackend.model.Tweet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

	List<Tweet> findByCreatedAtBeforeOrderByCreatedAtDesc(OffsetDateTime createdAt, Pageable pageable);
}
