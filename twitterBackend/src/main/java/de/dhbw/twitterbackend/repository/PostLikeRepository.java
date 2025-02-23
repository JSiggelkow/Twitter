package de.dhbw.twitterbackend.repository;

import de.dhbw.twitterbackend.model.Post;
import de.dhbw.twitterbackend.model.PostLike;
import de.dhbw.twitterbackend.model.PostLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, PostLikeId> {

	Long countByPost(Post post);
}
