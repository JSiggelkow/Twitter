package de.dhbw.twitterbackend.repository;

import de.dhbw.twitterbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
