package com.ingweb.backend.service;

import com.ingweb.backend.dao.UsuarioRepository;
import com.ingweb.backend.model.dto.LugarDTO;
import com.ingweb.backend.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LugarService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MapaService mapaService;
    @Autowired
    private FirebaseService firebaseService;

    public List<LugarDTO> getLugaresByUsuario(String usuarioEmail){
        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(usuarioEmail);

        return usuario.getLugares();
    }

    public LugarDTO crearLugar(String emailUsuario, LugarDTO lugarDTO) {
        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(emailUsuario);

        usuario.getLugares().add(lugarDTO);
        usuarioRepository.save(usuario);
        return lugarDTO;
    }

}
