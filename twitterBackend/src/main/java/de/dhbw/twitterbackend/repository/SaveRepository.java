package de.dhbw.twitterbackend.repository;

import de.dhbw.twitterbackend.model.Post;
import de.dhbw.twitterbackend.model.Save;
import de.dhbw.twitterbackend.model.SaveId;
import de.dhbw.twitterbackend.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;

public interface SaveRepository extends JpaRepository<Save, SaveId> {

	@Query("select s.post from Save s where s.user = :user and s.post.createdAt < :timeLimit order by s.post.createdAt desc")
	List<Post> findAllSavedPostsByUserAndSavedAtBefore(@Param("user")User user, @Param("timeLimit")OffsetDateTime createdAt, Pageable pageable);
}
