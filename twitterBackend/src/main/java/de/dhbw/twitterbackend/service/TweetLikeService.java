package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.model.TweetLike;
import de.dhbw.twitterbackend.model.TweetLikeId;
import de.dhbw.twitterbackend.repository.TweetLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TweetLikeService {

	private final TweetLikeRepository tweetLikeRepository;

	/* Private Methods */

	private TweetLike save(TweetLike tweetLike) {
		return tweetLikeRepository.save(tweetLike);
	}

	private void deleteById(TweetLikeId id) {
		tweetLikeRepository.deleteById(id);
	}

	private List<TweetLike> findAll() {
		return tweetLikeRepository.findAll();
	}

	private Optional<TweetLike> findById(TweetLikeId id) {
		return tweetLikeRepository.findById(id);
	}
}
