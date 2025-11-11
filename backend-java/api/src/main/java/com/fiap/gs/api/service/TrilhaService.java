package com.fiap.gs.api.service;

import com.fiap.gs.api.domain.Trilha;
import com.fiap.gs.api.dto.TrilhaRequest;
import com.fiap.gs.api.dto.TrilhaResponse;
import com.fiap.gs.api.repository.TrilhaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class TrilhaService {

    private final TrilhaRepository repo;

    public TrilhaService(TrilhaRepository repo) {
        this.repo = repo;
    }

    public TrilhaResponse create(@NonNull TrilhaRequest req) {
        Trilha t = new Trilha();
        t.setNome(req.nome());
        t.setDescricao(req.descricao());
        t = repo.save(t);
        return toResponse(t);
    }

    public Page<TrilhaResponse> list(String nome, @NonNull Pageable pageable) {
        Page<Trilha> page = (nome != null && !nome.isBlank())
                ? repo.findByNomeContainingIgnoreCase(nome, pageable)
                : repo.findAll(pageable);
        return page.map(this::toResponse);
    }

    public TrilhaResponse get(@NonNull Long id) {
        return toResponse(findEntity(id));
    }

    public TrilhaResponse update(@NonNull Long id, @NonNull TrilhaRequest req) {
        Trilha existente = findEntity(id);
        if (req.nome() != null && !req.nome().isBlank()) existente.setNome(req.nome());
        if (req.descricao() != null) existente.setDescricao(req.descricao());
        existente = repo.save(existente);
        return toResponse(existente);
    }

    public void delete(@NonNull Long id) {
        Trilha existente = findEntity(id);
        repo.delete(existente);
    }

    private Trilha findEntity(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trilha " + id + " não encontrada"));
    }

    private TrilhaResponse toResponse(Trilha t) {
        int total = (t.getModulos() == null) ? 0 : t.getModulos().size();
        return new TrilhaResponse(t.getId(), t.getNome(), t.getDescricao(), total);
    }
}
