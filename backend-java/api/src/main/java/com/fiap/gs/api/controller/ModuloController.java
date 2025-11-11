package com.fiap.gs.api.controller;

import com.fiap.gs.api.dto.ModuloRequest;
import com.fiap.gs.api.dto.ModuloResponse;
import com.fiap.gs.api.service.ModuloService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/modulos")
@Tag(name = "Módulos")
public class ModuloController {

    private final ModuloService service;

    public ModuloController(ModuloService service) {
        this.service = service;
    }

    @PostMapping
    public ModuloResponse create(@RequestBody @Valid ModuloRequest req) {
        return service.create(req);
    }

    @GetMapping
    public Page<ModuloResponse> list(
            @RequestParam(required = false) String titulo,
            @PageableDefault(size = 10, sort = "titulo") Pageable pageable
    ) {
        return service.list(titulo, pageable);
    }

    @GetMapping("{id}")
    public ModuloResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("{id}")
    public ModuloResponse update(@PathVariable Long id, @RequestBody @Valid ModuloRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
