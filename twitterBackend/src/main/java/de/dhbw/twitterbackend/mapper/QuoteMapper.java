package de.dhbw.twitterbackend.mapper;

import de.dhbw.twitterbackend.dto.QuoteDTO;
import de.dhbw.twitterbackend.model.Tweet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuoteMapper {

	public QuoteDTO toDTO(Tweet tweet) {
		return new QuoteDTO(
				tweet.getId(),
				tweet.getText(),
				tweet.getUser().getUsername(),
				tweet.getCreatedAt()
		);
	}
}
