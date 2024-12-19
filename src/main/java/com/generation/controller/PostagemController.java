package com.generation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.generation.model.PostagemModel;
import com.generation.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    @Autowired
    private PostagemRepository postagemRepository;

    @GetMapping
    public ResponseEntity<List<PostagemModel>> getAll() {
        return ResponseEntity.ok(postagemRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostagemModel> getById(@PathVariable Long id) {
        return postagemRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<PostagemModel>> getByTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @PostMapping
    public ResponseEntity<PostagemModel> post(@RequestBody PostagemModel postagem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
    }

    @PutMapping
    public ResponseEntity<PostagemModel> put(@RequestBody PostagemModel postagem) {
        if (postagem.getId() == null || !postagemRepository.existsById(postagem.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!postagemRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        postagemRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
