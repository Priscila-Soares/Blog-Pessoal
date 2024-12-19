package com.generation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.generation.model.Usuario;
import com.generation.model.UsuarioLogin;
import com.generation.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
            return Optional.empty(); 
        }

        usuario.setSenha(criptografarSenha(usuario.getSenha()));
        return Optional.of(usuarioRepository.save(usuario));
    }

    public Optional<Usuario> atualizarUsuario(Usuario usuario) {
        if (usuarioRepository.findById(usuario.getId()).isPresent()) {
            usuario.setSenha(criptografarSenha(usuario.getSenha()));
            return Optional.of(usuarioRepository.save(usuario));
        }
        return Optional.empty();
    }

    public Optional<UsuarioLogin> autenticarUsuario(UsuarioLogin usuarioLogin) {
        Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.getUsuario());

        if (usuario.isPresent() && compararSenhas(usuarioLogin.getSenha(), usuario.get().getSenha())) {
            usuarioLogin.setToken("Bearer " + gerarToken(usuarioLogin.getUsuario()));
            return Optional.of(usuarioLogin);
        }
        return Optional.empty();
    }

    private String criptografarSenha(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }

    private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(senhaDigitada, senhaBanco);
    }

    private String gerarToken(String usuario) {
        return "TOKEN_DE_EXEMPLO"; 
    }
}