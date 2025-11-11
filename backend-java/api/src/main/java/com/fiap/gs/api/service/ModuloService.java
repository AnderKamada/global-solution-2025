package com.fiap.gs.api.service;

import com.fiap.gs.api.domain.Modulo;
import com.fiap.gs.api.domain.Trilha;
import com.fiap.gs.api.dto.ModuloRequest;
import com.fiap.gs.api.dto.ModuloResponse;
import com.fiap.gs.api.repository.ModuloRepository;
import com.fiap.gs.api.repository.TrilhaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class ModuloService {

    private final ModuloRepository moduloRepo;
    private final TrilhaRepository trilhaRepo;

    public ModuloService(ModuloRepository moduloRepo, TrilhaRepository trilhaRepo) {
        this.moduloRepo = moduloRepo;
        this.trilhaRepo = trilhaRepo;
    }

    public ModuloResponse create(@NonNull ModuloRequest req) {
        Trilha trilha = trilhaRepo.findById(req.trilhaId())
                .orElseThrow(() -> new EntityNotFoundException("Trilha " + req.trilhaId() + " não encontrada"));

        Modulo m = new Modulo();
        m.setTitulo(req.titulo());
        m.setTrilha(trilha);

        m = moduloRepo.save(m);
        return toResponse(m);
    }

    public Page<ModuloResponse> list(String titulo, @NonNull Pageable pageable) {
        Page<Modulo> page = (titulo != null && !titulo.isBlank())
                ? moduloRepo.findByTituloContainingIgnoreCase(titulo, pageable)
                : moduloRepo.findAll(pageable);
        return page.map(this::toResponse);
    }

    public ModuloResponse get(@NonNull Long id) {
        return toResponse(findEntity(id));
    }

    public ModuloResponse update(@NonNull Long id, @NonNull ModuloRequest req) {
        Modulo existente = findEntity(id);

        if (req.titulo() != null && !req.titulo().isBlank()) {
            existente.setTitulo(req.titulo());
        }
        if (req.trilhaId() != null) {
            Trilha trilha = trilhaRepo.findById(req.trilhaId())
                    .orElseThrow(() -> new EntityNotFoundException("Trilha " + req.trilhaId() + " não encontrada"));
            existente.setTrilha(trilha);
        }

        existente = moduloRepo.save(existente);
        return toResponse(existente);
    }

    public void delete(@NonNull Long id) {
        Modulo existente = findEntity(id);
        moduloRepo.delete(existente);
    }

    private Modulo findEntity(Long id) {
        return moduloRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Módulo " + id + " não encontrado"));
    }

    private ModuloResponse toResponse(Modulo m) {
        return new ModuloResponse(m.getId(),
                m.getTrilha() != null ? m.getTrilha().getId() : null,
                m.getTitulo());
    }
}
