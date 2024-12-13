package com.ingweb.backend.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.ingweb.backend.model.dto.EventoDTO;
import com.ingweb.backend.model.dto.UbicacionDTO;
import com.ingweb.backend.model.dto.UsuarioDTO;
import com.ingweb.backend.service.EventoService;
import com.ingweb.backend.service.FirebaseService;
import com.ingweb.backend.service.MapaService;
import com.ingweb.backend.service.UsuarioService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
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
    private EventoService eventoService;

    @PostMapping("/usuario/registroGoogle")
    public UsuarioDTO registroGoogle(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.registrarUsuario(usuarioDTO);
    }

    @GetMapping("/usuario/autenticacion")
    public UsuarioDTO identificarUsuario(@RequestHeader("Authorization") String authValue) throws FirebaseAuthException {
        return firebaseService.verifyToken(authValue);
    }

    @GetMapping("/evento/{id}")
    public EventoDTO getEvento(@PathVariable("id") ObjectId id){
        return eventoService.getEvento(id);
    }

    @GetMapping("/evento/proximos")
    public List<EventoDTO> getEventosProximos(@RequestParam("direccion") String direccion) throws UnsupportedEncodingException {
        return eventoService.getEventosProximos(direccion);
    }

    @GetMapping("/evento/organizador/{organizador}")
    public List<EventoDTO> getEventosByOrganizador(@PathVariable("organizador") String organizador){
        return eventoService.getEventosByOrganizador(organizador);
    }

    @PostMapping("/evento")
    public EventoDTO createEvento(@RequestBody EventoDTO eventoDTO) {
        return eventoService.crearEvento(eventoDTO);
    }

    @PutMapping("/evento/{nombreEvento}")
    public EventoDTO modificarEvento(@PathVariable("nombreEvento") String nombreEvento, @RequestBody EventoDTO eventoDTO) {
        return eventoService.modificarEvento(nombreEvento, eventoDTO);
    }

    @DeleteMapping("/evento/{nombreEvento}")
    public void borrarEvento(@PathVariable("nombreEvento") String nombreEvento) {
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
