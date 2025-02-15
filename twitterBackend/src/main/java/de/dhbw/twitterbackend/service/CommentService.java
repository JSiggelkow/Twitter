package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.model.Comment;
import de.dhbw.twitterbackend.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;

	/* Private Methods */

	private Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

	private void deleteById(Long id) {
		commentRepository.deleteById(id);
	}

	private List<Comment> findAll() {
		return commentRepository.findAll();
	}

	private Optional<Comment> findById(Long id) {
		return commentRepository.findById(id);
	}
}
