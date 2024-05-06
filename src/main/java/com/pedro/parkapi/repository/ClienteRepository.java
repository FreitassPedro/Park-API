package com.pedro.parkapi.repository;

import com.pedro.parkapi.entity.Cliente;
import com.pedro.parkapi.repository.projections.ClienteProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("select c from Cliente c")
    Page<ClienteProjection> findAllPeagle(Pageable pageable);

    Cliente findByUsuarioId(Long id);
}
