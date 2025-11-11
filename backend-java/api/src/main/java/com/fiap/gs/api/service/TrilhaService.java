package com.fiap.gs.api.service;

import com.fiap.gs.api.domain.Trilha;
import com.fiap.gs.api.dto.TrilhaRequest;
import com.fiap.gs.api.dto.TrilhaResponse;
import com.fiap.gs.api.repository.TrilhaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.lang.NonNull;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class TrilhaService {

    private final TrilhaRepository repo;

    public TrilhaService(TrilhaRepository repo) { this.repo = repo; }

    public Page<Trilha> listar(@NonNull Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Trilha buscarPorId(@NonNull Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trilha " + id + " não encontrada"));
    }
}