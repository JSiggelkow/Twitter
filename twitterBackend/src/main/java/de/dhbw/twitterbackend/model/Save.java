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
	@JoinColumn(name = "userid", nullable = false)
	private User user;

	@MapsId("tweetId")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "tweetid", nullable = false)
	private Tweet tweet;

	@ColumnDefault("now()")
	@Column(name = "savedat", nullable = false)
	private OffsetDateTime savedAt;

	@PrePersist
	public void prePersist() {
		if (this.savedAt == null) {
			this.savedAt = OffsetDateTime.now();
		}
	}
}