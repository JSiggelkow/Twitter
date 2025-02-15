package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.model.User;
import de.dhbw.twitterbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	/* Private Methods */

	public User save(User user) {
		return userRepository.save(user);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}
}
