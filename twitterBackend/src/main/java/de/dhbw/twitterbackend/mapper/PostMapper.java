package de.dhbw.twitterbackend.mapper;

import de.dhbw.twitterbackend.dto.CreatePostDTO;
import de.dhbw.twitterbackend.dto.PostDTO;
import de.dhbw.twitterbackend.model.Retweet;
import de.dhbw.twitterbackend.model.Post;
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

	public PostDTO toDTO(Post post, UserPrincipal userPrincipal) {
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
				postLikeService.isPostLikedByUser(post, userService.findByUsername(userPrincipal.getUsername())),
				retweetService.isPostRetweetedByUser(post, userService.findByUsername(userPrincipal.getUsername())));
	}

	/**
	 * overloaded toDTO methods for retweets without text
	 */
	public PostDTO toDTO(UserPrincipal userPrincipal, Retweet retweet) {
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
				postLikeService.isPostLikedByUser(retweet.getPost(), userService.findByUsername(userPrincipal.getUsername())),
				retweet.getUser().getUsername().equals(userPrincipal.getUsername()));
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
