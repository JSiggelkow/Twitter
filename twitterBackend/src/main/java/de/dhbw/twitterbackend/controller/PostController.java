package de.dhbw.twitterbackend.controller;

import de.dhbw.twitterbackend.dto.CreatePostDTO;
import de.dhbw.twitterbackend.dto.PostDTO;
import de.dhbw.twitterbackend.dto.StatusDTO;
import de.dhbw.twitterbackend.mapper.PostMapper;
import de.dhbw.twitterbackend.mapper.StatusMapper;
import de.dhbw.twitterbackend.model.Post;
import de.dhbw.twitterbackend.security.UserPrincipal;
import de.dhbw.twitterbackend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * The PostController class handles requests related to posts, including creating posts, retrieving posts,
 * toggling actions such as likes, retweets, and saves, and fetching saved posts. It utilizes various services
 * and mappers to perform operations and manage post-related functionality.
 */
@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	private final PostMapper postMapper;
	private final PostLikeService postLikeService;
	private final RetweetService retweetService;
	private final UserService userService;
	private final FeedService feedService;
	private final StatusMapper statusMapper;
	private final SaveService saveService;

	@PostMapping
	public ResponseEntity<PostDTO> post(@RequestBody CreatePostDTO createPostDTO, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		return ResponseEntity.ok(postMapper.toDTO(
				postService.postTweet(
						postMapper.toPost(createPostDTO, userPrincipal), userPrincipal), userPrincipal));
	}

	/**
	 * Retrieves a list of the newest posts limited by a specified number.
	 *
	 * @param limit the maximum number of posts to retrieve. Defaults to 25 if not specified.
	 * @param userPrincipal the authenticated user making the request.
	 * @return a list of the newest posts as PostDTOs wrapped in a ResponseEntity.
	 */
	@GetMapping("/newest")
	public ResponseEntity<List<PostDTO>> getNewestByLimit(@RequestParam(defaultValue = "25") int limit, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		return ResponseEntity.ok(feedService.fetchByLimitAndAfterTime(limit, OffsetDateTime.now(), userPrincipal));
	}

	/**
	 * Retrieves a list of tweets created before a specified timestamp and limited by a specified number of results.
	 *
	 * @param createdAt the timestamp to filter tweets that were created before this time.
	 * @param limit the maximum number of tweets to retrieve. Defaults to 10 if not specified.
	 * @param userPrincipal the authenticated user making the request.
	 * @return a list of tweets as PostDTOs wrapped in a ResponseEntity.
	 */
	@GetMapping("/before")
	public ResponseEntity<List<PostDTO>> getTweetsBeforeCreatedAtByLimit(
			@RequestParam OffsetDateTime createdAt,
			@RequestParam(defaultValue = "10") int limit,
			@AuthenticationPrincipal UserPrincipal userPrincipal) {
		return ResponseEntity.ok(feedService.fetchByLimitAndAfterTime(limit, createdAt, userPrincipal));
	}

	/**
	 * Retrieves the status of a parent post, including its comments, based on the provided parameters.
	 *
	 * @param parentPostId the ID of the parent post for which the status and comments are to be retrieved.
	 * @param createdAt an optional parameter to filter comments created before the specified timestamp. If not provided, the current timestamp is used.
	 * @param limit the maximum number of comments to retrieve. Defaults to 20 if not specified.
	 * @param userPrincipal the authenticated user making the request.
	 * @return a ResponseEntity containing the status of the parent post as a StatusDTO.
	 */
	@GetMapping("/status")
	public ResponseEntity<StatusDTO> getCommentsForPost(
			@RequestParam Long parentPostId,
			@RequestParam(required = false) OffsetDateTime createdAt,
			@RequestParam(defaultValue = "20") int limit,
			@AuthenticationPrincipal UserPrincipal userPrincipal) {

		Post parentPost = postService.findById(parentPostId);

		return ResponseEntity.ok(statusMapper.toDTO(
				parentPost,
				createdAt == null ? OffsetDateTime.now() : createdAt,
				limit, userPrincipal
		));
	}

	/**
	 * Retrieves a list of saved posts for the authenticated user, filtered by an optional creation timestamp
	 * and limited to the specified number of results.
	 *
	 * @param createdAt an optional filter for retrieving posts saved before the specified timestamp
	 *                  (if null, the current timestamp is used).
	 * @param limit the maximum number of saved posts to retrieve (default is 25).
	 * @param userPrincipal the authenticated user's principal information.
	 * @return a ResponseEntity containing a list of PostDTO objects representing the saved posts.
	 */
	@GetMapping("/saved")
	public ResponseEntity<List<PostDTO>> getSavedByUser(
			@RequestParam(required = false) OffsetDateTime createdAt,
			@RequestParam(defaultValue = "25") int limit,
			@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		return ResponseEntity.ok(saveService.findAllSavedPostsByUserAndSavedAtBefore(
				userService.findByUsername(userPrincipal.getUsername()),
				createdAt != null ? createdAt : OffsetDateTime.now(),
				limit
		).stream().map(post -> postMapper.toDTO(post, userPrincipal)).toList());
	}


	@PostMapping("/like")
	public ResponseEntity<Void> toggleLike(@RequestParam long postId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		postLikeService.toggleLike(
				postService.findById(postId),
				userService.findByUsername(userPrincipal.getUsername())
		);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/retweet")
	public ResponseEntity<Void> toggleRetweet(@RequestParam long postId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		retweetService.toggleRetweet(
				postService.findById(postId),
				userService.findByUsername(userPrincipal.getUsername())
		);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/save")
	public ResponseEntity<Void> toggleSave(@RequestParam long postId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		saveService.toggleSave(
				postService.findById(postId),
				userService.findByUsername(userPrincipal.getUsername())
		);

		return ResponseEntity.ok().build();
	}

}
