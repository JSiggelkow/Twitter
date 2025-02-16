package de.dhbw.twitterbackend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * This JwtIssuer creates a JWT token with the User specific claims
 */

@Component
@RequiredArgsConstructor
public class JwtIssuer {
	@Value("${security.jwt.secret-key}")
	private String jwtKey;

	public String createJWT(Long userId, String username, List<String> roles) {
		return JWT.create()
				.withSubject(String.valueOf(userId))
				.withExpiresAt(Instant.now().plus(Duration.of(31, ChronoUnit.DAYS)))
				.withClaim("u", username)
				.withClaim("a", roles)
				.sign(Algorithm.HMAC256(jwtKey));
	}
}
