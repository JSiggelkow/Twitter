package de.dhbw.twitterbackend.mapper;

import de.dhbw.twitterbackend.dto.QuoteDTO;
import de.dhbw.twitterbackend.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuoteMapper {

	public QuoteDTO toDTO(Post post) {
		return new QuoteDTO(
				post.getId(),
				post.getText(),
				post.getUser().getUsername(),
				post.getCreatedAt()
		);
	}
}
