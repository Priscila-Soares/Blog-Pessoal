package com.generation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.generation.model.Usuario;
import com.generation.model.UsuarioLogin;
import com.generation.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        return usuarioService.cadastrarUsuario(usuario)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/logar")
    public ResponseEntity<UsuarioLogin> autenticar(@RequestBody UsuarioLogin usuarioLogin) {
        return usuarioService.autenticarUsuario(usuarioLogin)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(401).build());
    }
}
