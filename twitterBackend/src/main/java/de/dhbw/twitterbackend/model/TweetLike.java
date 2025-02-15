package de.dhbw.twitterbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "tweetLikes")
public class TweetLike {
	@SequenceGenerator(name = "tweetLikes_id_gen", sequenceName = "tweet_id_seq", allocationSize = 1)
	@EmbeddedId
	private TweetLikeId id;

	@MapsId("userId")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	@MapsId("tweetId")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "tweetId", nullable = false)
	private Tweet tweet;

	@ColumnDefault("now()")
	@Column(name = "likedAt", nullable = false)
	private OffsetDateTime likedAt;

}