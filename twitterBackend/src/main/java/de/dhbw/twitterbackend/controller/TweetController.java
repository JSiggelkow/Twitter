package de.dhbw.twitterbackend.controller;

import de.dhbw.twitterbackend.dto.TweetDTO;
import de.dhbw.twitterbackend.mapper.TweetMapper;
import de.dhbw.twitterbackend.model.Tweet;
import de.dhbw.twitterbackend.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tweet")
@RequiredArgsConstructor
public class TweetController {

	private final TweetService tweetService;
	private final TweetMapper tweetMapper;

	@PostMapping
	public ResponseEntity<TweetDTO> createTweet(@RequestBody Tweet tweet) {
		return ResponseEntity.ok(tweetMapper.toDTO(tweetService.save(tweet)));
	}

	@GetMapping
	public ResponseEntity<List<TweetDTO>> getAllTweet() {
		return ResponseEntity.ok(tweetService.findAll()
				.stream()
				.map(tweetMapper::toDTO)
				.toList()
		);
	}

}
