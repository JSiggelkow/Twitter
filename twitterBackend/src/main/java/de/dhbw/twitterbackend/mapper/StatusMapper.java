package de.dhbw.twitterbackend.mapper;

import de.dhbw.twitterbackend.dto.StatusDTO;
import de.dhbw.twitterbackend.model.Post;
import de.dhbw.twitterbackend.security.UserPrincipal;
import de.dhbw.twitterbackend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
public class StatusMapper {

	private final PostService postService;
	private final PostMapper postMapper;

	public StatusDTO toDTO(Post parent, OffsetDateTime timeLimit, int limit, UserPrincipal userPrincipal) {
		return new StatusDTO(
				postMapper.toDTO(parent, userPrincipal),
				postService.findAllByCommentOn(parent, timeLimit, limit).stream()
						.map(comment -> postMapper.toDTO(comment, userPrincipal))
						.toList());

	}
}
