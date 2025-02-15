package de.dhbw.twitterbackend.repository;

import de.dhbw.twitterbackend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
