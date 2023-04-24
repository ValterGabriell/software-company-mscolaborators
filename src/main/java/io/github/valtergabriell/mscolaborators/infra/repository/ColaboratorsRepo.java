package io.github.valtergabriell.mscolaborators.infra.repository;

import io.github.valtergabriell.mscolaborators.application.domain.Colaborators;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ColaboratorsRepo extends JpaRepository<Colaborators, Long> {

    Optional<Colaborators> findByLead(Long lead);
}
