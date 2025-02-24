package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.model.Post;
import de.dhbw.twitterbackend.model.Save;
import de.dhbw.twitterbackend.model.SaveId;
import de.dhbw.twitterbackend.model.User;
import de.dhbw.twitterbackend.repository.SaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SaveService {

	private final SaveRepository saveRepository;

	private void save(Save save) {
		saveRepository.save(save);
	}

	public void toggleSave(Post post, User user) {
		if (isPostSavedByUser(post, user)) {
			unSavePost(post, user);
		} else {
			savePost(post, user);
		}
	}

	public void savePost(Post post, User user) {
		Save save = new Save();
		save.setId(new SaveId(user.getId(), post.getId()));
		save.setPost(post);
		save.setUser(user);
		this.save(save);
	}

	public void unSavePost(Post post, User user) {
		saveRepository.deleteById(new SaveId(user.getId(), post.getId()));
	}

	public boolean isPostSavedByUser(Post post, User user) {
		return saveRepository.existsById(new SaveId(user.getId(), post.getId()));
	}

	/**
	 * Retrieves a list of posts saved by a specific user, filtered by posts created before a specified timestamp,
	 * with a limit on the number of results.
	 *
	 * @param user the user whose saved posts are to be retrieved
	 * @param createdAt the timestamp that filters posts created before this time
	 * @param limit the maximum number of posts to return
	 * @return a list of saved posts for the specified user, created before the given timestamp, up to the specified limit
	 */
	public List<Post> findAllSavedPostsByUserAndSavedAtBefore(User user, OffsetDateTime createdAt, int limit) {
		return saveRepository.findAllSavedPostsByUserAndSavedAtBefore(user, createdAt, PageRequest.of(0, limit));
	}
}
