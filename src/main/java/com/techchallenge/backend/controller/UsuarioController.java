/**
 * Autor: Marco Ezequiel Cedro Barros Borges
 * Data: 25 de mai. de 2025
 */

package com.techchallenge.backend.controller;

import com.techchallenge.backend.dto.UsuarioDTO;
import com.techchallenge.backend.config.JwtUtil;
import com.techchallenge.backend.dto.LoginDTO;
import com.techchallenge.backend.model.Usuario;
import com.techchallenge.backend.service.UsuarioService;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;
    
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/cadastrar")
    public Usuario cadastrar(@RequestBody UsuarioDTO dto) {
        return service.cadastrar(dto);
    }

    @PutMapping("/atualizar/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        if (service.validarLogin(loginDTO)) {       	
            String token = jwtUtil.gerarToken(loginDTO.login);
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }


    @PostMapping("/trocar-senha/{id}")
    public boolean trocarSenha(@PathVariable Long id, @RequestBody String novaSenha) {
        return service.trocarSenha(id, novaSenha);
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
}
