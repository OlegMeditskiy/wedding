package ru.dasha.wedding.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dasha.wedding.domain.Story;

import java.util.Optional;

@Repository
public interface StoryRepo extends JpaRepository<Story, Long> {
    Optional<Story> findById(Long id);
}
