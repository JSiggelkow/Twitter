package de.dhbw.twitterbackend.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * This class represents a UserPrincipalAuthenticationToken for a UserPrincipal
 */
public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {

	private final UserPrincipal principal;

	public UserPrincipalAuthenticationToken(UserPrincipal principal) {
		super(principal.getAuthorities());
		this.principal = principal;
		setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

}
