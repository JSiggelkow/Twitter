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
public class PostLikeId implements java.io.Serializable {
	@Serial
	private static final long serialVersionUID = -4911786253279269910L;
	@Column(name = "userid", nullable = false)
	private Long userId;

	@Column(name = "postid", nullable = false)
	private Long postId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		PostLikeId entity = (PostLikeId) o;
		return Objects.equals(this.userId, entity.userId) &&
				Objects.equals(this.postId, entity.postId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, postId);
	}

}