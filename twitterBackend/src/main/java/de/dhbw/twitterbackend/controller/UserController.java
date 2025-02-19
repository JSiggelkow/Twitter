package de.dhbw.twitterbackend.controller;

import de.dhbw.twitterbackend.model.User;
import de.dhbw.twitterbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<Void> signUp(@RequestBody @Validated User user) {
		userService.signUp(user.getUsername(), user.getPassword(), user.getEmail());
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/exists/username")
	public boolean checkUsernameExists(@RequestParam String username) {
		return userService.checkUsernameExists(username);
	}

	@GetMapping("/exists/email")
	public boolean checkEmailExists(@RequestParam String email) {
		return userService.checkEmailExists(email);
	}
}
