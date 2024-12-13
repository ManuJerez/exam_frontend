package com.ingweb.backend.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.ingweb.backend.model.dto.LugarDTO;
import com.ingweb.backend.model.dto.UbicacionDTO;
import com.ingweb.backend.model.dto.UsuarioDTO;
import com.ingweb.backend.service.LugarService;
import com.ingweb.backend.service.FirebaseService;
import com.ingweb.backend.service.MapaService;
import com.ingweb.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class Controller {

    @Autowired
    private FirebaseService firebaseService;
    @Autowired
    private MapaService mapaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LugarService lugarService;

    @PostMapping("/usuario/registroGoogle")
    public UsuarioDTO registroGoogle(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.registrarUsuario(usuarioDTO);
    }

    @GetMapping("/usuario/autenticacion")
    public UsuarioDTO identificarUsuario(@RequestHeader("Authorization") String authValue) throws FirebaseAuthException {
        return firebaseService.verifyToken(authValue);
    }

    @GetMapping("/lugar/{usuarioEmail}")
    public List<LugarDTO> getLugaresByUsuario(@PathVariable("usuarioEmail") String usuarioEmail){
        return lugarService.getLugaresByUsuario(usuarioEmail);
    }

    @PutMapping("/lugar")
    public void crearLugar(@RequestParam String emailUsuario,@RequestParam String direccion) {
        mapaService.anyadirUbicacion(emailUsuario, direccion);
    }

    @GetMapping("/ubicacion")
    public UbicacionDTO getUbicacion(@RequestParam String direccion) {
        return mapaService.getCoordenadas(direccion);
    }

}
