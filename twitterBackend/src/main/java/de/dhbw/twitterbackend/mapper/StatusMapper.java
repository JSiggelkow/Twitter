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

	/**
	 * Converts a parent Post entity along with its associated comments to a StatusDTO.
	 * The parent post and its comments are transformed into PostDTO objects,
	 * and the comments are filtered and limited based on the provided time and limit criteria.
	 *
	 * @param parent the parent Post entity to be converted
	 * @param timeLimit the upper time limit for filtering associated comments
	 * @param limit the maximum number of comments to include
	 * @param userPrincipal the UserPrincipal representing the currently logged-in user
	 * @return a StatusDTO containing the parent PostDTO and a list of associated comments as PostDTOs
	 */
	public StatusDTO toDTO(Post parent, OffsetDateTime timeLimit, int limit, UserPrincipal userPrincipal) {
		return new StatusDTO(
				postMapper.toDTO(parent, userPrincipal),
				postService.findAllByCommentOn(parent, timeLimit, limit).stream()
						.map(comment -> postMapper.toDTO(comment, userPrincipal))
						.toList());

	}
}
