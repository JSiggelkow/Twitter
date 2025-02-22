package de.dhbw.twitterbackend.service;

import de.dhbw.twitterbackend.security.JwtIssuer;
import de.dhbw.twitterbackend.security.UserPrincipal;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final JwtIssuer jwtIssuer;
	private final AuthenticationManager authenticationManager;

	@Value("${spring.config.frontend-domain}")
	private String domain;

	public void attemptLogin(HttpServletResponse response, String username, String password) {

		UserPrincipal userPrincipal = authenticateUser(username, password);
		String token = jwtIssuer.createJWT(userPrincipal.getUserId(), userPrincipal.getUsername(), List.of());

		response.addHeader(HttpHeaders.SET_COOKIE, createResponseCookie(token, Duration.of(31, ChronoUnit.DAYS)).toString());
	}

	public void attemptLogout(HttpServletResponse response) {
		response.addHeader(HttpHeaders.SET_COOKIE, createResponseCookie("", Duration.of(0, ChronoUnit.SECONDS)).toString());
	}


	private UserPrincipal authenticateUser(String username, String password) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password)
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return (UserPrincipal) authentication.getPrincipal();
	}

	private ResponseCookie createResponseCookie(String token, Duration maxAge) {
		return ResponseCookie.from("accessToken", token)
				.httpOnly(true)
				.sameSite("Lax")
				.path("/")
				.maxAge(maxAge.getSeconds())
				.domain(domain)
				.build();
	}
}
