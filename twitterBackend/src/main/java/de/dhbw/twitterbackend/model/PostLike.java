package de.dhbw.twitterbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "postlikes")
public class PostLike {
	@SequenceGenerator(name = "tweetlikes_id_gen", sequenceName = "tweet_id_seq", allocationSize = 1)
	@EmbeddedId
	private PostLikeId id;

	@MapsId("userId")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userid", nullable = false)
	private User user;

	@MapsId("postId")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "postid", nullable = false)
	private Post post;

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