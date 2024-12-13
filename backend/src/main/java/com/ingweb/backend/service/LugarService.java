package com.ingweb.backend.service;

import com.ingweb.backend.dao.EventoRepository;
import com.ingweb.backend.model.dto.LugarDTO;
import com.ingweb.backend.model.dto.UbicacionDTO;
import com.ingweb.backend.model.entity.Evento;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventoService extends DTOService<LugarDTO, Evento> {

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private MapaService mapaService;
    @Autowired
    private FirebaseService firebaseService;

    public LugarDTO getEvento(ObjectId id){
        Evento evento = eventoRepository.findById(id).orElse(null);
        return evento.toDto();
    }

    public List<LugarDTO> getEventosProximos(String direccion) throws UnsupportedEncodingException {
        List<LugarDTO> eventos = new ArrayList<>();
        String direccionDecoded = URLDecoder.decode(direccion, "UTF-8");
        UbicacionDTO ubicacionDTO = mapaService.getCoordenadas(direccionDecoded);
        List<Evento> eventosProximos = eventoRepository.findByLatAndLonOrderByTimestamp(ubicacionDTO.getLat(), ubicacionDTO.getLon());
        return entidadesADTO(eventosProximos);
    }

    public List<LugarDTO> getEventosByOrganizador(String organizador){
        List<Evento> eventosByOrganizador = eventoRepository.findByOrganizador(organizador);
        return entidadesADTO(eventosByOrganizador);
    }

    public LugarDTO crearEvento(LugarDTO lugarDTO) {
        Evento evento = new Evento();
        evento.setNombre(lugarDTO.getNombre());
        evento.setTimestamp(lugarDTO.getTimestamp());
        evento.setLugar(lugarDTO.getNombre());
        evento.setOrganizador(lugarDTO.getOrganizador());
        evento.setImagen(lugarDTO.getImagen());

        UbicacionDTO ubicacionDTO = mapaService.getCoordenadas(lugarDTO.getNombre());

        evento.setLat(ubicacionDTO.getLat());
        evento.setLon(ubicacionDTO.getLon());

        eventoRepository.save(evento);
        return evento.toDto();
    }

    public LugarDTO modificarEvento(String nombreEvento, LugarDTO lugarDTO) {
        Evento evento = eventoRepository.findByNombre(nombreEvento);
        if(evento != null){
            if(lugarDTO.getNombre() != null){
                evento.setNombre(lugarDTO.getNombre());
            }
            if(lugarDTO.getTimestamp() != null){
                evento.setTimestamp(lugarDTO.getTimestamp());
            }
            if(lugarDTO.getNombre() != null){
                evento.setLugar(lugarDTO.getNombre());
                UbicacionDTO ubicacionDTO = mapaService.getCoordenadas(lugarDTO.getNombre());
                evento.setLat(ubicacionDTO.getLat());
                evento.setLon(ubicacionDTO.getLon());
            }
            if(lugarDTO.getImagen() != null){
                evento.setImagen(lugarDTO.getImagen());
            }
            eventoRepository.save(evento);
        }else {
            throw new IllegalArgumentException("Evento no encontrado");
        }
        return evento.toDto();
    }

    public void borrarEvento(String nombreEvento) {
        Evento evento = eventoRepository.findByNombre(nombreEvento);
        if(evento != null){
            eventoRepository.delete(evento);
        }else {
            throw new IllegalArgumentException("Evento no encontrado");
        }
    }
}
