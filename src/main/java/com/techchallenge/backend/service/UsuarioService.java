/**
 * Autor: Marco Ezequiel Cedro Barros Borges
 * Data: 25 de mai. de 2025
 */

package com.techchallenge.backend.service;

import com.techchallenge.backend.dto.UsuarioDTO;
import com.techchallenge.backend.dto.LoginDTO;
import com.techchallenge.backend.model.Usuario;

public interface UsuarioService {
    Usuario cadastrar(UsuarioDTO dto);
    Usuario atualizar(Long id, UsuarioDTO dto);
    void excluir(Long id);
    Usuario buscarPorId(Long id);
    boolean validarLogin(LoginDTO loginDTO);
    boolean trocarSenha(Long id, String novaSenha);
}
