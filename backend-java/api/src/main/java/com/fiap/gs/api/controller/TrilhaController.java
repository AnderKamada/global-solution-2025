package com.fiap.gs.api.controller;

import com.fiap.gs.api.dto.TrilhaRequest;
import com.fiap.gs.api.dto.TrilhaResponse;
import com.fiap.gs.api.service.TrilhaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trilhas")
@Tag(name = "Trilhas")
public class TrilhaController {

    private final TrilhaService service;

    public TrilhaController(TrilhaService service) {
        this.service = service;
    }

    @PostMapping
    public TrilhaResponse create(@RequestBody @Valid TrilhaRequest req) {
        return service.create(req);
    }

    @GetMapping
    public Page<TrilhaResponse> list(
            @RequestParam(required = false) String nome,
            @PageableDefault(size = 10, sort = "nome") Pageable pageable
    ) {
        return service.list(nome, pageable);
    }

    @GetMapping("{id}")
    public TrilhaResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("{id}")
    public TrilhaResponse update(@PathVariable Long id, @RequestBody @Valid TrilhaRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
