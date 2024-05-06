package com.pedro.parkapi.repository;

import com.pedro.parkapi.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface VagaRepository extends JpaRepository<Vaga, Long> {

    Optional<Vaga> findByCodigo(String codigo);

    Optional<Vaga> findFirstByStatus(Vaga.StatusVaga statusVaga);
}
