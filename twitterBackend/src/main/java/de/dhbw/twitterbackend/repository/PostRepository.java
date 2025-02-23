package de.dhbw.twitterbackend.repository;

import de.dhbw.twitterbackend.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByCreatedAtBeforeOrderByCreatedAtDesc(OffsetDateTime createdAt, Pageable pageable);

	long countPostByCommenton(Post commentOn);

	List<Post> findAllByCommenton(Post commentOn);
}


