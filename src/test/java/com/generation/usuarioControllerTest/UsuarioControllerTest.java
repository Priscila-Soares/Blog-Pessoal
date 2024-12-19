package com.generation.usuarioControllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void deveRetornarListaDeUsuarios() {
        ResponseEntity<String> resposta = testRestTemplate
            .withBasicAuth("usuario", "senha")
            .exchange("/usuarios", HttpMethod.GET, null, String.class);

        assertEquals(200, resposta.getStatusCodeValue());
    }
}