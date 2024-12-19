package com.generation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.generation.model.TemaModel;
import com.generation.repository.TemaRepository;

@RestController
@RequestMapping("/temas")
public class TemaController {

    @Autowired
    private TemaRepository temaRepository;

    @GetMapping
    public ResponseEntity<List<TemaModel>> getAll() {
        return ResponseEntity.ok(temaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemaModel> getById(@PathVariable Long id) {
        return temaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<TemaModel>> getByDescricao(@PathVariable String descricao) {
        return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
    }

    @PostMapping
    public ResponseEntity<TemaModel> post(@RequestBody TemaModel tema) {
        return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema));
    }

    @PutMapping
    public ResponseEntity<TemaModel> put(@RequestBody TemaModel tema) {
        if (tema.getId() == null || !temaRepository.existsById(tema.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(temaRepository.save(tema));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!temaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        temaRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
