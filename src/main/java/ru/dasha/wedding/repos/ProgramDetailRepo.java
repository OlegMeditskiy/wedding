package ru.dasha.wedding.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dasha.wedding.domain.ProgramDetail;

import java.util.Optional;

public interface ProgramDetailRepo extends JpaRepository<ProgramDetail,Long> {
    Optional<ProgramDetail> findById(Long id);
}
