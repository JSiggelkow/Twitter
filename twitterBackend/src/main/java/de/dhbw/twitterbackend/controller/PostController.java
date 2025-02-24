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

	@GetMapping("/newest")
	public ResponseEntity<List<PostDTO>> getNewestByLimit(@RequestParam(defaultValue = "25") int limit, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		return ResponseEntity.ok(feedService.fetchByLimitAndAfterTime(limit, OffsetDateTime.now(), userPrincipal));
	}

	@GetMapping("/before")
	public ResponseEntity<List<PostDTO>> getTweetsBeforeCreatedAtByLimit(
			@RequestParam OffsetDateTime createdAt,
			@RequestParam(defaultValue = "10") int limit,
			@AuthenticationPrincipal UserPrincipal userPrincipal) {
		return ResponseEntity.ok(feedService.fetchByLimitAndAfterTime(limit, createdAt, userPrincipal));
	}

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
}
