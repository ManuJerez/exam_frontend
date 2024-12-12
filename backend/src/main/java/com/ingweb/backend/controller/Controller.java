package com.ingweb.backend.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.ingweb.backend.model.dto.EventoDTO;
import com.ingweb.backend.model.dto.UbicacionDTO;
import com.ingweb.backend.model.dto.UsuarioDTO;
import com.ingweb.backend.service.EventoService;
import com.ingweb.backend.service.FirebaseService;
import com.ingweb.backend.service.MapaService;
import com.ingweb.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class Controller {

    @Autowired
    private FirebaseService firebaseService;
    @Autowired
    private MapaService mapaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EventoService eventoService;

    @PostMapping("/usuario/registroGoogle")
    public UsuarioDTO registroGoogle(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.registarUsuario(usuarioDTO);
    }

    @GetMapping("/usuario/autenticacion")
    public UsuarioDTO identificarUsuario(@RequestParam String idToken) throws FirebaseAuthException {
        return firebaseService.verifyToken(idToken);
    }

    @PostMapping("/evento")
    public EventoDTO createEvento(@RequestBody EventoDTO eventoDTO) {
        return eventoService.crearEvento(eventoDTO);
    }

    @PutMapping("/evento/{nombreEvento}")
    public EventoDTO modificarEvento(@PathVariable("nombreEvento") String nombreEvento, @RequestBody EventoDTO eventoDTO){
        return eventoService.modificarEvento(nombreEvento, eventoDTO);
    }

    @DeleteMapping("/evento/{nombreEvento}")
    public void borrarEvento(@PathVariable("nombreEvento") String nombreEvento){
        eventoService.borrarEvento(nombreEvento);
    }

    @GetMapping("/ubicacion")
    public UbicacionDTO getUbicacion(@RequestParam String direccion) {
        return mapaService.getCoordenadas(direccion);
    }

    @PutMapping("/ubicacion/{nombreEvento}")
    public void anyadirUbicacion(@PathVariable("nombreEvento") String nombreEvento, @RequestParam String direccion){
        mapaService.anyadirUbicacion(nombreEvento, direccion);
    }

}
