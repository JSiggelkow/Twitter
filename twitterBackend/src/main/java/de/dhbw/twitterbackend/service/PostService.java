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

	/**
	 * Retrieves a list of comments associated with the specified parent post,
	 * which were created before the given time limit, limited to a specified number of results.
	 *
	 * @param parent the parent post for which comments are to be retrieved
	 * @param timeLimit the upper time limit; only comments created before this time will be included
	 * @param limit the maximum number of comments to retrieve
	 * @return a list of comments associated with the specified parent post
	 */
	public List<Post> findAllByCommentOn(Post parent, OffsetDateTime timeLimit, int limit) {
		return postRepository.findAllCommentsByParentPostAndCreatedAtBefore(parent, timeLimit, PageRequest.of(0, limit));
	}

	/**
	 * Retrieves a list of non-comment posts created before the specified time limit,
	 * limited to a specified number of posts.
	 *
	 * @param limit the maximum number of posts to retrieve
	 * @param timeLimit the upper time limit; only posts created before this time will be included
	 * @return a list of posts
	 */
	public List<Post> getByLimitAndTimeLimit(int limit, OffsetDateTime timeLimit) {
		return postRepository.findAllNonCommentPostsByCreatedAtBefore(timeLimit ,PageRequest.of(0, limit));
	}

	public Long countPostsByCommentOn(Post commentOn) {
		return postRepository.countPostByCommenton(commentOn);
	}
}
