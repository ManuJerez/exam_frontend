package com.ingweb.backend.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.ingweb.backend.model.dto.UsuarioDTO;
import com.ingweb.backend.service.FirebaseService;
import com.ingweb.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private FirebaseService firebaseService;

    @PostMapping()
    public UsuarioDTO registroGoogle(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.registarUsuario(usuarioDTO);
    }

    @GetMapping("/identificar")
    public UsuarioDTO identificarUsuario(@RequestParam String idToken) throws FirebaseAuthException {
        return firebaseService.verifyToken(idToken);
    }

}
