package de.dhbw.twitterbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "commentLikes")
public class CommentLike {
	@SequenceGenerator(name = "commentLikes_id_gen", sequenceName = "tweet_id_seq", allocationSize = 1)
	@EmbeddedId
	private CommentLikeId id;

	@MapsId("userId")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userId", nullable = false)
	private User userId;

	@MapsId("commentId")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "commentId", nullable = false)
	private Comment comment;

	@ColumnDefault("now()")
	@Column(name = "likedAt", nullable = false)
	private OffsetDateTime likedAT;

}