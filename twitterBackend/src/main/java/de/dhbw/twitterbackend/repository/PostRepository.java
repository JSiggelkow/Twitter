package de.dhbw.twitterbackend.repository;

import de.dhbw.twitterbackend.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

	@Query("select p from Post p where p.createdAt < :timeLimit and p.commenton is null order by p.createdAt desc")
	List<Post> findAllNonCommentPostsByCreatedAtBefore(@Param("timeLimit")OffsetDateTime createdAt, Pageable pageable);

	long countPostByCommenton(Post commentOn);

	@Query("select p from Post p where p.commenton = :parent and p.createdAt < :timeLimit order by p.createdAt desc")
	List<Post> findAllCommentsByParentPostAndCreatedAtBefore(@Param("parent")Post commentOn, @Param("timeLimit")OffsetDateTime createdAt, Pageable pageable);
}


