package io.github.valtergabriell.mscolaborators.infra.repository;

import io.github.valtergabriell.mscolaborators.application.domain.Colaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ColaboratorsRepo extends JpaRepository<Colaborator, Long> {

    Optional<Colaborator> findByLead(Long lead);

    @Query(value = "SELECT c.lead FROM Colaborator c WHERE c.id = ?1")
    Long returnLeadId(Long id);

    @Query(value = "SELECT c FROM Colaborator c WHERE c.isActive = false")
    List<Colaborator> returnAllInactivesColaborators();

    @Query(value = "SELECT c FROM Colaborator c WHERE c.isActive = true")
    List<Colaborator> returnAllActivesColaborators();
}
