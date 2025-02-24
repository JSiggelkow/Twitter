package de.dhbw.twitterbackend.config;

import de.dhbw.twitterbackend.security.CustomUserDetailsService;
import de.dhbw.twitterbackend.security.JwtAuthenticationFilter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final CustomUserDetailsService customUserDetailsService;

	private static final String DEFAULT_API = "/api/**";

	/**
	 * This Method sets the security rules for the application
	 */
	@Bean
	public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {

		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		http
				.csrf(AbstractHttpConfigurer::disable)
				.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.formLogin(AbstractHttpConfigurer::disable)
				.securityMatcher(DEFAULT_API)
				.authorizeHttpRequests(reg -> reg
						.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
						.requestMatchers("/api/auth/login").permitAll()
						.requestMatchers("/api/user/signup").permitAll()
						.requestMatchers("/api/user/exists/**").permitAll()
						.anyRequest().authenticated()
				);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * This method sets the password Encoder and customUserDetailsService in the Spring Security AuthenticationManager
	 */
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authBuilder
				.userDetailsService(customUserDetailsService)
				.passwordEncoder(passwordEncoder());

		return authBuilder.build();
	}

	/**
	 * this config configures the cors settings and enables the frontend ulr for cors
	 */
	@Bean
	public WebMvcConfigurer corsConfig() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@NonNull CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins(
								"http://localhost:4200"
						)
						.allowedMethods(
								HttpMethod.GET.name(),
								HttpMethod.PUT.name(),
								HttpMethod.POST.name(),
								HttpMethod.DELETE.name()
						)
						.allowedHeaders(
								HttpHeaders.CONTENT_TYPE,
								HttpHeaders.AUTHORIZATION
						)
						.allowCredentials(true);
			}
		};
	}
}
