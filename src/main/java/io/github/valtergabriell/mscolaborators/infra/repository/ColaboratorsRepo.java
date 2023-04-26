package io.github.valtergabriell.mscolaborators.infra.repository;

import io.github.valtergabriell.mscolaborators.application.domain.Colaborator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ColaboratorsRepo extends JpaRepository<Colaborator, Long> {

    Optional<Colaborator> findByLead(Long lead);
}
