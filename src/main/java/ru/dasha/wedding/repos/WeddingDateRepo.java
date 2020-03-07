package ru.dasha.wedding.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dasha.wedding.domain.AboutUs;
import ru.dasha.wedding.domain.WeddingDate;

import java.util.Optional;

public interface WeddingDateRepo extends JpaRepository<WeddingDate,Long> {
    Optional<WeddingDate> findById(Long id);
}
