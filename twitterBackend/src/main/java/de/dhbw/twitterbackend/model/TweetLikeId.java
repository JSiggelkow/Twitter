package de.dhbw.twitterbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TweetLikeId implements java.io.Serializable {
	@Serial
	private static final long serialVersionUID = -4911786253279269910L;
	@Column(name = "userid", nullable = false)
	private Long userId;

	@Column(name = "tweetid", nullable = false)
	private Long tweetId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		TweetLikeId entity = (TweetLikeId) o;
		return Objects.equals(this.userId, entity.userId) &&
				Objects.equals(this.tweetId, entity.tweetId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, tweetId);
	}

}