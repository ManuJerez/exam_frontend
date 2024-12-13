package com.ingweb.backend.service;

import com.ingweb.backend.dao.UsuarioRepository;
import com.ingweb.backend.model.dto.UsuarioDTO;
import com.ingweb.backend.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(usuarioDTO.getEmail());

        return usuario != null ? handleInicio(usuarioDTO) : handleRegistro(usuarioDTO);
    }

    private UsuarioDTO handleRegistro(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setUid(usuarioDTO.getUid());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setLugares(new ArrayList<>());
        usuarioRepository.save(usuario);
        return usuario.toDto();
    }

    private UsuarioDTO handleInicio(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(usuarioDTO.getEmail());
        return usuario.toDto();
    }

    public UsuarioDTO getUsuarioByEmail(String email){
        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(email);
        return usuario.toDto();
    }
}
