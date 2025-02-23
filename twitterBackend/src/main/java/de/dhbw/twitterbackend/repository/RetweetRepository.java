package de.dhbw.twitterbackend.repository;

import de.dhbw.twitterbackend.model.Retweet;
import de.dhbw.twitterbackend.model.RetweetId;
import de.dhbw.twitterbackend.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;

public interface RetweetRepository extends JpaRepository<Retweet, RetweetId> {

	Long countByPost(Post post);

	@Query("Select r from Retweet r where r.post.createdAt < :timeLimit order by r.post.createdAt desc")
	List<Retweet> findByTweetCreatedAtOrderByTweetCreatedAtDesc(@Param("timeLimit")OffsetDateTime createdAt, Pageable pageable);
}
