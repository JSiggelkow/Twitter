package de.dhbw.twitterbackend.controller;

import de.dhbw.twitterbackend.model.User;
import de.dhbw.twitterbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	//TODO Change User creation to UserDTO an add proper auth
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.save(user));
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		return ResponseEntity.ok(userService.findAll());
	}
}
