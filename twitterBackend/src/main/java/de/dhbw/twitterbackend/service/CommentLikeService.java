package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.model.CommentLike;
import de.dhbw.twitterbackend.model.CommentLikeId;
import de.dhbw.twitterbackend.repository.CommentLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

	private final CommentLikeRepository commentLikeRepository;

	/* Private Methods */

	public CommentLike save(CommentLike commentLike) {
		return commentLikeRepository.save(commentLike);
	}

	public void deleteById(CommentLikeId id) {
		commentLikeRepository.deleteById(id);
	}

	public List<CommentLike> findAll() {
		return commentLikeRepository.findAll();
	}

	public Optional<CommentLike> findById(CommentLikeId id) {
		return commentLikeRepository.findById(id);
	}
}
