package de.dhbw.twitterbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_gen")
	@SequenceGenerator(name = "comment_id_gen", sequenceName = "comment_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "text", length = Integer.MAX_VALUE)
	private String text;

	@Column(name = "video", length = Integer.MAX_VALUE)
	private String video;

	@Column(name = "image", length = Integer.MAX_VALUE)
	private String image;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userid", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "tweetid", nullable = false)
	private Tweet tweet;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentcommentid")
	private Comment parentComment;

	@ColumnDefault("now()")
	@Column(name = "createdat", nullable = false)
	private OffsetDateTime createdAt;

	@PrePersist
	public void prePersist() {
		if (this.createdAt == null) {
			this.createdAt = OffsetDateTime.now();
		}
	}

}