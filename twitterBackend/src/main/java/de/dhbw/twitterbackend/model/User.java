package de.dhbw.twitterbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_gen")
	@SequenceGenerator(name = "users_id_gen", sequenceName = "users_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "username", nullable = false, length = 20)
	@NotEmpty(message = "Username must not be empty")
	@NotNull(message = "Username must not be null")
	@Pattern(regexp = "^\\w+$", message = "Username must contain only letters, numbers and underscores")
	@Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
	private String username;

	@Column(name = "password", nullable = false, length = Integer.MAX_VALUE)
	@NotEmpty(message = "Password must not be empty")
	@NotNull(message = "Password must not be null")
	@Pattern(regexp = "^\\S+$", message = "Password must not contain spaces")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	@Size(max = 255, message = "Password must not be longer than 255 characters")
	private String password;

	@Column(name = "email", nullable = false)
	@NotEmpty(message = "Email must not be empty")
	@NotNull(message = "Email must not be null")
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
			message = "The given email doesn't match the given pattern")
	private String email;

	@ColumnDefault("now()")
	@Column(name = "createdat", nullable = false)
	private OffsetDateTime createdAt;

	@PrePersist
	public void prePersist() {
		if (this.createdAt == null) {
			this.createdAt = OffsetDateTime.now();
		}
	}
}