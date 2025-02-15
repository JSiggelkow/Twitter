package de.dhbw.twitterbackend.repository;

import de.dhbw.twitterbackend.model.Retweet;
import de.dhbw.twitterbackend.model.RetweetId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetweetRepository extends JpaRepository<Retweet, RetweetId> {
}
