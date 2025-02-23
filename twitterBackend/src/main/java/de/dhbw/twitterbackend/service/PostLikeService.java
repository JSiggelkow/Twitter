package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.model.Post;
import de.dhbw.twitterbackend.model.PostLike;
import de.dhbw.twitterbackend.model.PostLikeId;
import de.dhbw.twitterbackend.model.User;
import de.dhbw.twitterbackend.repository.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostLikeService {

	private final PostLikeRepository postLikeRepository;

	public void save(PostLike postLike) {
		postLikeRepository.save(postLike);
	}

	/**
	 * if user has liked a tweet it gets unliked
	 * else the tweet gets liked
	 */
	public void toggleLike(Post post, User user) {
		if (isPostLikedByUser(post, user)) {
			unlikePost(post, user);
		} else {
			likePost(post, user);
		}
	}

	public void likePost(Post post, User user) {
		PostLike postLike = new PostLike();
		postLike.setId(new PostLikeId(user.getId(), post.getId()));
		postLike.setPost(post);
		postLike.setUser(user);
		save(postLike);
	}

	public void unlikePost(Post post, User user) {
		postLikeRepository.deleteById(new PostLikeId(user.getId(), post.getId()));
	}

	public Long countByPost(Post post) {
		return postLikeRepository.countByPost(post);
	}

	public boolean isPostLikedByUser(Post post, User user) {
		return postLikeRepository.existsById(new PostLikeId(user.getId(), post.getId()));
	}
}
