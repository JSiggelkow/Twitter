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
	 * overloaded toDTO methods for retweets without text
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
