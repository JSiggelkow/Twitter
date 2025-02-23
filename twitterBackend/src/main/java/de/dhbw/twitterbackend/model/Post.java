package de.dhbw.twitterbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tweet_id_gen")
	@SequenceGenerator(name = "tweet_id_gen", sequenceName = "tweet_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "text", length = Integer.MAX_VALUE)
	private String text;

	@Column(name = "video", length = Integer.MAX_VALUE)
	private String video;

	@Column(name = "image", length = Integer.MAX_VALUE)
	private String image;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "retweetid")
	private Post retweetId;

	@ColumnDefault("now()")
	@Column(name = "createdat", nullable = false)
	private OffsetDateTime createdAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commenton")
	private Post commenton;

	@PrePersist
	public void prePersist() {
		if (this.createdAt == null) {
			this.createdAt = OffsetDateTime.now();
		}
	}
}