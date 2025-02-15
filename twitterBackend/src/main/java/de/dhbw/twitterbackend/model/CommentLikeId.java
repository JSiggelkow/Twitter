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
public class CommentLikeId implements java.io.Serializable {
	@Serial
	private static final long serialVersionUID = 872487230343291588L;
	@Column(name = "userid", nullable = false)
	private Long userId;

	@Column(name = "commentid", nullable = false)
	private Long commentId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		CommentLikeId entity = (CommentLikeId) o;
		return Objects.equals(this.commentId, entity.commentId) &&
				Objects.equals(this.userId, entity.userId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(commentId, userId);
	}

}