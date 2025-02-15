package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.model.Save;
import de.dhbw.twitterbackend.model.SaveId;
import de.dhbw.twitterbackend.repository.SaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaveService {

	private final SaveRepository saveRepository;

	/* Private Methods a */

	private Save save(Save save) {
		return saveRepository.save(save);
	}

	private void deleteById(SaveId id) {
		saveRepository.deleteById(id);
	}

	private List<Save> findAll() {
		return saveRepository.findAll();
	}

	private Optional<Save> findById(SaveId id) {
		return saveRepository.findById(id);
	}

}
