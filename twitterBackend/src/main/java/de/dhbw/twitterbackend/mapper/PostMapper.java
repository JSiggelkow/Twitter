package de.dhbw.twitterbackend.mapper;

import de.dhbw.twitterbackend.dto.CreatePostDTO;
import de.dhbw.twitterbackend.dto.PostDTO;
import de.dhbw.twitterbackend.model.Retweet;
import de.dhbw.twitterbackend.model.Post;
import de.dhbw.twitterbackend.model.User;
import de.dhbw.twitterbackend.security.UserPrincipal;
import de.dhbw.twitterbackend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMapper {

	private final PostLikeService postLikeService;
	private final RetweetService retweetService;
	private final UserService userService;
	private final QuoteMapper quoteMapper;
	private final PostService postService;
	private final SaveService saveService;

	/**
	 * Converts a Post entity to a PostDTO, including additional details such as the current user's interactions with the post.
	 *
	 * @param post the Post entity to be converted
	 * @param userPrincipal the UserPrincipal representing the currently logged-in user
	 * @return a PostDTO containing the details of the post along with user interaction statuses
	 */
	public PostDTO toDTO(Post post, UserPrincipal userPrincipal) {

		User user = userService.findByUsername(userPrincipal.getUsername());

		return new PostDTO(post.getId(),
				post.getText(),
				post.getVideo(),
				post.getImage(),
				post.getUser().getUsername(),
				null,
				post.getRetweetId() != null ? quoteMapper.toDTO(post.getRetweetId()) : null,
				post.getCreatedAt(),
				postLikeService.countByPost(post),
				retweetService.countByTweet(post),
				postService.countPostsByCommentOn(post),
				postLikeService.isPostLikedByUser(post, user),
				retweetService.isPostRetweetedByUser(post, user),
				saveService.isPostSavedByUser(post, user));
	}

	/**
	 * Converts a Retweet entity to a PostDTO, including information about the original post,
	 * the user who retweeted it, and details about the current user's interactions with the post.
	 *
	 * @param userPrincipal the UserPrincipal representing the currently authenticated user
	 * @param retweet the Retweet entity containing information about the retweeted post
	 * @return a PostDTO containing the details of the retweeted post, user interaction statuses,
	 *         and the user who retweeted it
	 */
	public PostDTO toDTO(UserPrincipal userPrincipal, Retweet retweet) {

		User user = userService.findByUsername(userPrincipal.getUsername());

		return new PostDTO(retweet.getPost().getId(),
				retweet.getPost().getText(),
				retweet.getPost().getVideo(),
				retweet.getPost().getImage(),
				retweet.getPost().getUser().getUsername(),
				retweet.getUser().getUsername(),
				retweet.getPost().getRetweetId() != null ? quoteMapper.toDTO(retweet.getPost().getRetweetId()) : null,
				retweet.getPost().getCreatedAt(),
				postLikeService.countByPost(retweet.getPost()),
				retweetService.countByTweet(retweet.getPost()),
				postService.countPostsByCommentOn(retweet.getPost()),
				postLikeService.isPostLikedByUser(retweet.getPost(), user),
				retweet.getUser().getUsername().equals(userPrincipal.getUsername()),
				saveService.isPostSavedByUser(retweet.getPost(), user));
	}

	/**
	 * Converts a CreatePostDTO and a UserPrincipal to a Post entity.
	 * This method maps the text from CreatePostDTO, associates the requesting user from UserPrincipal,
	 * and associates retweet or comment relationships if specified in the input DTO.
	 *
	 * @param createPostDTO the DTO containing the details of the post to be created,
	 *                      including text, retweetId (if the post is a retweet),
	 *                      and commentOn (if the post is a comment).
	 * @param userPrincipal the user principal object containing information about the currently authenticated user.
	 * @return a populated Post entity with the specified details and relationships.
	 */
	public Post toPost(CreatePostDTO createPostDTO, UserPrincipal userPrincipal) {
		Post post = new Post();
		post.setText(createPostDTO.text());
		post.setUser(userService.findByUsername(userPrincipal.getUsername()));
		if (createPostDTO.retweetId() != null) {
			post.setRetweetId(postService.findById(createPostDTO.retweetId()));
		}
		if (createPostDTO.commentOn() != null) {
			post.setCommenton(postService.findById(createPostDTO.commentOn()));
		}
		return post;
	}
}
