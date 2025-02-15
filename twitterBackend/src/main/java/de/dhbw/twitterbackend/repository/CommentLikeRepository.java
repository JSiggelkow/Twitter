package de.dhbw.twitterbackend.repository;

import de.dhbw.twitterbackend.model.CommentLike;
import de.dhbw.twitterbackend.model.CommentLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, CommentLikeId> {
}
