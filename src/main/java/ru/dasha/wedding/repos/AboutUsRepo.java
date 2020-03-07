package ru.dasha.wedding.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dasha.wedding.domain.AboutUs;

import java.util.Optional;

@Repository
public interface AboutUsRepo extends JpaRepository<AboutUs,Long> {
    Optional<AboutUs> findById(Long id);
}
