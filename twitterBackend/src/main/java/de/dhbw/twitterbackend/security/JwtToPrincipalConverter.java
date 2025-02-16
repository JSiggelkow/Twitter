package de.dhbw.twitterbackend.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

/**
 * Converts a DecodedJWT token into a UserPrincipal
 */

@Component
public class JwtToPrincipalConverter {

	public UserPrincipal convert(DecodedJWT jwt) {
		return UserPrincipal.builder()
				.userId(Long.parseLong(jwt.getSubject()))
				.username(jwt.getClaim("u").asString())
				.build();
	}

}
