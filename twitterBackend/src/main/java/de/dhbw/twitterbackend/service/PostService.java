package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.exceptions.PostNotFoundException;
import de.dhbw.twitterbackend.model.Post;
import de.dhbw.twitterbackend.repository.PostRepository;
import de.dhbw.twitterbackend.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final UserService userService;

	public Post save(Post post) {
		return postRepository.save(post);
	}

	public Post findById(Long id) {
		return postRepository.findById(id)
				.orElseThrow(() -> new PostNotFoundException(id));
	}

	public Post postTweet(Post post, UserPrincipal userPrincipal) {
		post.setUser(userService.findByUsername(userPrincipal.getUsername()));

		return save(post);
	}

	public List<Post> findAllByCommentOn(Post parent, OffsetDateTime timeLimit, int limit) {
		return postRepository.findAllCommentsByParentPostAndCreatedAtBefore(parent, timeLimit, PageRequest.of(0, limit));
	}

	/**
	 * Gets the newest Tweets in descending order by created At from the Database
	 * this is important for the initial loading request for the main feed
	 *
	 */
	public List<Post> getByLimitAndTimeLimit(int limit, OffsetDateTime timeLimit) {
		return postRepository.findAllNonCommentPostsByCreatedAtBefore(timeLimit ,PageRequest.of(0, limit));
	}

	public Long countPostsByCommentOn(Post commentOn) {
		return postRepository.countPostByCommenton(commentOn);
	}
}
