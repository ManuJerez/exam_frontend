package com.ingweb.backend.service;

import com.ingweb.backend.dao.UsuarioRepository;
import com.ingweb.backend.model.dto.UsuarioDTO;
import com.ingweb.backend.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDTO registarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(usuarioDTO.getEmail());

        return usuario != null ? handleInicio(usuarioDTO) : handleRegistro(usuarioDTO);
    }

    private UsuarioDTO handleRegistro(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setUid(usuarioDTO.getUid());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setContrasenya("12345");
        usuarioRepository.save(usuario);
        return usuario.toDto();
    }

    private UsuarioDTO handleInicio(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(usuarioDTO.getEmail());
        return usuario.toDto();
    }
}
