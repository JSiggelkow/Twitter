package de.dhbw.twitterbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "save")
public class Save {
	@SequenceGenerator(name = "save_id_gen", sequenceName = "tweet_id_seq", allocationSize = 1)
	@EmbeddedId
	private SaveId id;

	@MapsId("userId")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userId", nullable = false)
	private User userId;

	@MapsId("tweetId")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "tweetId", nullable = false)
	private Tweet tweetId;

	@ColumnDefault("now()")
	@Column(name = "savedAt", nullable = false)
	private OffsetDateTime savedAt;

}