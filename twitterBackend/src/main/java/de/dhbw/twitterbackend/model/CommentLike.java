package de.dhbw.twitterbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "commentlikes")
public class CommentLike {
	@SequenceGenerator(name = "commentlikes_id_gen", sequenceName = "tweet_id_seq", allocationSize = 1)
	@EmbeddedId
	private CommentLikeId id;

	@MapsId("userId")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userid", nullable = false)
	private User user;

	@MapsId("commentId")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "commentid", nullable = false)
	private Comment comment;

	@ColumnDefault("now()")
	@Column(name = "likedat", nullable = false)
	private OffsetDateTime likedAt;

	@PrePersist
	public void prePersist() {
		if (this.likedAt == null) {
			this.likedAt = OffsetDateTime.now();
		}
	}

}