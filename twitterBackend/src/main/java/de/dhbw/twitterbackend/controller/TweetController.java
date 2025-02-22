package de.dhbw.twitterbackend.controller;

import de.dhbw.twitterbackend.dto.TweetDTO;
import de.dhbw.twitterbackend.mapper.TweetMapper;
import de.dhbw.twitterbackend.model.Tweet;
import de.dhbw.twitterbackend.security.UserPrincipal;
import de.dhbw.twitterbackend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tweet")
@RequiredArgsConstructor
public class TweetController {

	private final TweetService tweetService;
	private final TweetMapper tweetMapper;
	private final TweetLikeService tweetLikeService;
	private final RetweetService retweetService;
	private final UserService userService;
	private final FeedService feedService;

	@PostMapping
	public ResponseEntity<TweetDTO> postTweet(@RequestBody Tweet tweet, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		return ResponseEntity.ok(tweetMapper.toDTO(
				tweetService.postTweet(tweet, userPrincipal), userPrincipal));
	}

	@GetMapping("/newest")
	public ResponseEntity<List<TweetDTO>> getNewestByLimit(@RequestParam(defaultValue = "25") int limit, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		return ResponseEntity.ok(feedService.fetchNewestByLimit(limit, userPrincipal));
	}

	@GetMapping("/before")
	public ResponseEntity<List<TweetDTO>> getTweetsBeforeCreatedAtByLimit(
			@RequestParam OffsetDateTime createdAt,
			@RequestParam(defaultValue = "10") int limit,
			@AuthenticationPrincipal UserPrincipal userPrincipal) {
		return ResponseEntity.ok(tweetService.getTweetsBeforeCreatedAtByLimit(createdAt, limit).stream()
				.map(tweet -> tweetMapper.toDTO(tweet, userPrincipal))
				.toList());
	}

	@PostMapping("/like")
	public ResponseEntity<Void> toggleLike(@RequestParam long tweetId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		tweetLikeService.toggleLike(
				tweetService.findById(tweetId),
				userService.findByUsername(userPrincipal.getUsername())
		);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/retweet")
	public ResponseEntity<Void> retweet(@RequestParam long tweetId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		retweetService.retweet(
				tweetService.findById(tweetId),
				userService.findByUsername(userPrincipal.getUsername())
		);

		return ResponseEntity.ok().build();
	}
}
