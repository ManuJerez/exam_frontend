package com.ingweb.backend.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.ingweb.backend.dao.UsuarioRepository;
import com.ingweb.backend.model.dto.UsuarioDTO;
import com.ingweb.backend.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDTO verifyToken(String authValue) throws FirebaseAuthException {
        String token = authValue.replace("Bearer ", "").trim();
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        String uid = decodedToken.getUid();
        Usuario usuario = new Usuario();
        if (uid != null) {
            usuario = usuarioRepository.findByUid(uid);
        }
        return usuario.toDto();
    }
}
