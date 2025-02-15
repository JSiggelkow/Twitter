package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.model.User;
import de.dhbw.twitterbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	/* Private Methods */

	private User save(User user) {
		return userRepository.save(user);
	}

	private void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	private List<User> findAll() {
		return userRepository.findAll();
	}

	private Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

}
