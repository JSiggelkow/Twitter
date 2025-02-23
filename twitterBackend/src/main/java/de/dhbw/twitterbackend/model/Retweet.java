package de.dhbw.twitterbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "retweet")
public class Retweet {
	@SequenceGenerator(name = "retweet_id_gen", sequenceName = "tweet_id_seq", allocationSize = 1)
	@EmbeddedId
	private RetweetId id;

	@MapsId("userId")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userid", nullable = false)
	private User user;

	@MapsId("postId")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "postid", nullable = false)
	private Post post;

	@ColumnDefault("now()")
	@Column(name = "retweetedat", nullable = false)
	private OffsetDateTime retweetedAt;

	@PrePersist
	public void prePersist() {
		if (this.retweetedAt == null) {
			this.retweetedAt = OffsetDateTime.now();
		}
	}
}