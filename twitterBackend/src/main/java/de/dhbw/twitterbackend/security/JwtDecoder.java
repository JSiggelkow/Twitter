package de.dhbw.twitterbackend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * This class decodes a JWT token with the given key
 */
@Component
@RequiredArgsConstructor
public class JwtDecoder {

	@Value("${security.jwt.secret-key}")
	private String jwtKey;

	public DecodedJWT decode(String token) {
		return JWT.require(Algorithm.HMAC256(jwtKey))
				.build()
				.verify(token);
	}
}
