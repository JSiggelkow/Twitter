package de.dhbw.twitterbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class RetweetId implements java.io.Serializable {
	@Serial
	private static final long serialVersionUID = 2483060559948464842L;
	@Column(name = "userid", nullable = false)
	private Long userId;

	@Column(name = "tweetid", nullable = false)
	private Long tweetId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		RetweetId entity = (RetweetId) o;
		return Objects.equals(this.userId, entity.userId) &&
				Objects.equals(this.tweetId, entity.tweetId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, tweetId);
	}

}