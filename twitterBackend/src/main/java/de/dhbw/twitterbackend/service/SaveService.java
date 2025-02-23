package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.model.Post;
import de.dhbw.twitterbackend.model.Save;
import de.dhbw.twitterbackend.model.SaveId;
import de.dhbw.twitterbackend.model.User;
import de.dhbw.twitterbackend.repository.SaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
}
