package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.model.Retweet;
import de.dhbw.twitterbackend.model.RetweetId;
import de.dhbw.twitterbackend.repository.RetweetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RetweetService {

	private final RetweetRepository retweetRepository;

	/* Private Methods '*/

	private Retweet save(Retweet retweet) {
		return retweetRepository.save(retweet);
	}

	private void deleteById(RetweetId id) {
		retweetRepository.deleteById(id);
	}

	private List<Retweet> findAll() {
		return retweetRepository.findAll();
	}

	private Optional<Retweet> findById(RetweetId id) {
		return retweetRepository.findById(id);
	}
}
