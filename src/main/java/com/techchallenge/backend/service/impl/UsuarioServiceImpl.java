/**
 * Autor: Marco Ezequiel Cedro Barros Borges
 * Data: 25 de mai. de 2025
 */

package com.techchallenge.backend.service.impl;

import com.techchallenge.backend.dto.UsuarioDTO;
import com.techchallenge.backend.dto.LoginDTO;
import com.techchallenge.backend.model.Usuario;
import com.techchallenge.backend.repository.UsuarioRepository;
import com.techchallenge.backend.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public Usuario cadastrar(UsuarioDTO dto) {
       
    	if (repository.existsByLogin(dto.login)) {
            throw new RuntimeException("Login já existe em nossa base de dados.");
        }

        if (repository.existsByEmail(dto.email)) {
            throw new RuntimeException("E-mail existe em nossa base de dados.");
        }
    	
    	Usuario usuario = new Usuario();
        usuario.setNome(dto.nome);
        usuario.setEmail(dto.email);
        usuario.setLogin(dto.login);
        usuario.setEndereco(dto.endereco);
        usuario.setTipo(dto.tipo);
        usuario.setSenha(encoder.encode(dto.senha));
        usuario.setDataUltimaAlteracao(LocalDate.now());
        return repository.save(usuario);
    }

    @Override
    public Usuario atualizar(Long id, UsuarioDTO dto) {
        Usuario usuario = repository.findById(id).orElseThrow();
        usuario.setNome(dto.nome);
        usuario.setEmail(dto.email);
        usuario.setLogin(dto.login);
        usuario.setEndereco(dto.endereco);
        usuario.setTipo(dto.tipo);
        usuario.setDataUltimaAlteracao(LocalDate.now());
        return repository.save(usuario);
    }

    @Override
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public boolean validarLogin(LoginDTO loginDTO) {
        return repository.findByLogin(loginDTO.login)
                .map(usuario -> encoder.matches(loginDTO.senha, usuario.getSenha()))
                .orElse(false);
    }

    @Override
    public boolean trocarSenha(Long id, String novaSenha) {
        Usuario usuario = repository.findById(id).orElseThrow();
        usuario.setSenha(encoder.encode(novaSenha));
        usuario.setDataUltimaAlteracao(LocalDate.now());
        repository.save(usuario);
        return true;
    }
}
