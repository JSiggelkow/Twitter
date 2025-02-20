package de.dhbw.twitterbackend.controller;

import de.dhbw.twitterbackend.dto.TweetDTO;
import de.dhbw.twitterbackend.mapper.TweetMapper;
import de.dhbw.twitterbackend.model.Tweet;
import de.dhbw.twitterbackend.security.UserPrincipal;
import de.dhbw.twitterbackend.service.TweetLikeService;
import de.dhbw.twitterbackend.service.TweetService;
import de.dhbw.twitterbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/tweet")
@RequiredArgsConstructor
public class TweetController {

	private final TweetService tweetService;
	private final TweetMapper tweetMapper;
	private final TweetLikeService tweetLikeService;
	private final UserService userService;

	@PostMapping
	public ResponseEntity<TweetDTO> postTweet(@RequestBody Tweet tweet, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		return ResponseEntity.ok(tweetMapper.toDTO(
				tweetService.postTweet(tweet, userPrincipal)));
	}

	@GetMapping
	public ResponseEntity<List<TweetDTO>> getAllTweet() {
		return ResponseEntity.ok(tweetService.findAll()
				.stream()
				.map(tweetMapper::toDTO)
				.sorted(Comparator.comparing(TweetDTO::createdAt).reversed())
				.toList()
		);
	}

	@PostMapping("/like/{tweetId}")
	public ResponseEntity<Void> likeTweet(@PathVariable long tweetId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		tweetLikeService.likeTweet(
				tweetService.findById(tweetId),
				userService.findByUsername(userPrincipal.getUsername())
		);

		return ResponseEntity.ok().build();
	}

}
