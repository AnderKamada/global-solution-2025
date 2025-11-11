package com.fiap.gs.api.service;

import com.fiap.gs.api.domain.Modulo;
import com.fiap.gs.api.domain.Trilha;
import com.fiap.gs.api.dto.ModuloRequest;
import com.fiap.gs.api.dto.ModuloResponse;
import com.fiap.gs.api.repository.ModuloRepository;
import com.fiap.gs.api.repository.TrilhaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.lang.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ModuloService {

    private final ModuloRepository repo;

    public ModuloService(ModuloRepository repo) { this.repo = repo; }

    public Page<Modulo> listar(@NonNull Pageable pageable) {
        return repo.findAll(pageable); // nunca retorna null
    }

    public Modulo buscarPorId(@NonNull Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Módulo " + id + " não encontrado"));
    }

    public Modulo atualizar(@NonNull Long id, @NonNull Modulo payload) {
        Modulo existente = buscarPorId(id);
        existente.setNome(Objects.requireNonNull(payload.getNome(), "nome"));
        // ... copie os demais campos garantindo não-nulo
        return repo.save(existente);
    }

    public void excluir(@NonNull Long id) {
        Modulo existente = buscarPorId(id);
        repo.delete(existente);
    }
}