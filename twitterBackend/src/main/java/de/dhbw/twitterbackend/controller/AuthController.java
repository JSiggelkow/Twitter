package de.dhbw.twitterbackend.controller;

import de.dhbw.twitterbackend.dto.LoginDTO;
import de.dhbw.twitterbackend.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<Void> login(HttpServletResponse response, @RequestBody LoginDTO loginDTO) {
		authService.attemptLogin(response, loginDTO.username(), loginDTO.password());
		return ResponseEntity.ok().build();
	}

	@GetMapping()
	public ResponseEntity<Void> check() {
		return ResponseEntity.ok().build();
	}
}
