package com.fiap.gs.api.repository;

import com.fiap.gs.api.domain.Modulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuloRepository extends JpaRepository<Modulo, Long> {
    Page<Modulo> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
}
