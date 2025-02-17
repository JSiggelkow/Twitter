package de.dhbw.twitterbackend.security;

import de.dhbw.twitterbackend.model.User;
import de.dhbw.twitterbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * This class loads UserInformation from the Database and builds a UserPrincipal
 */
@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);
		return UserPrincipal.builder()
				.userId(user.getId())
				.username(user.getUsername())
				.password(user.getPassword())
				.build();
	}
}
