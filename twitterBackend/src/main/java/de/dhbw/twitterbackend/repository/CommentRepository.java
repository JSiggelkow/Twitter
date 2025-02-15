package de.dhbw.twitterbackend.repository;

import de.dhbw.twitterbackend.model.Comment;
import de.dhbw.twitterbackend.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	Long countByTweet(Tweet tweet);
}
