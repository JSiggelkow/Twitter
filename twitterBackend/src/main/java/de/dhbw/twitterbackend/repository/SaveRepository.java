package de.dhbw.twitterbackend.repository;

import de.dhbw.twitterbackend.model.Save;
import de.dhbw.twitterbackend.model.SaveId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaveRepository extends JpaRepository<Save, SaveId> {
}
