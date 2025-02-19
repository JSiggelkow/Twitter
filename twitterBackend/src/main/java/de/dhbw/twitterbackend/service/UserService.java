package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.exceptions.UserNotFoundException;
import de.dhbw.twitterbackend.model.User;
import de.dhbw.twitterbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User save(User user) {
		return userRepository.save(user);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException(username));
	}

	public void signUp(String username, String password, String email) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(new BCryptPasswordEncoder().encode(password));
		user.setEmail(email);
		save(user);
	}

	public boolean checkUsernameExists(String username) {
		return userRepository.existsByUsername(username);
	}

	public boolean checkEmailExists(String email) {
		return userRepository.existsByEmail(email);
	}
}
