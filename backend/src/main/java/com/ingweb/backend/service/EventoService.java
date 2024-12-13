package com.ingweb.backend.service;

import com.ingweb.backend.dao.EventoRepository;
import com.ingweb.backend.model.dto.EventoDTO;
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
public class EventoService extends DTOService<EventoDTO, Evento> {

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private MapaService mapaService;
    @Autowired
    private FirebaseService firebaseService;

    public EventoDTO getEvento(ObjectId id){
        Evento evento = eventoRepository.findById(id).orElse(null);
        return evento.toDto();
    }

    public List<EventoDTO> getEventosProximos(String direccion) throws UnsupportedEncodingException {
        List<EventoDTO> eventos = new ArrayList<>();
        String direccionDecoded = URLDecoder.decode(direccion, "UTF-8");
        UbicacionDTO ubicacionDTO = mapaService.getCoordenadas(direccionDecoded);
        List<Evento> eventosProximos = eventoRepository.findByLatAndLonOrderByTimestamp(ubicacionDTO.getLat(), ubicacionDTO.getLon());
        return entidadesADTO(eventosProximos);
    }

    public List<EventoDTO> getEventosByOrganizador(String organizador){
        List<Evento> eventosByOrganizador = eventoRepository.findByOrganizador(organizador);
        return entidadesADTO(eventosByOrganizador);
    }

    public EventoDTO crearEvento(EventoDTO eventoDTO) {
        Evento evento = new Evento();
        evento.setNombre(eventoDTO.getNombre());
        evento.setTimestamp(eventoDTO.getTimestamp());
        evento.setLugar(eventoDTO.getLugar());
        evento.setOrganizador(eventoDTO.getOrganizador());
        evento.setImagen(eventoDTO.getImagen());

        UbicacionDTO ubicacionDTO = mapaService.getCoordenadas(eventoDTO.getLugar());

        evento.setLat(ubicacionDTO.getLat());
        evento.setLon(ubicacionDTO.getLon());

        eventoRepository.save(evento);
        return evento.toDto();
    }

    public EventoDTO modificarEvento(String nombreEvento, EventoDTO eventoDTO) {
        Evento evento = eventoRepository.findByNombre(nombreEvento);
        if(evento != null){
            if(eventoDTO.getNombre() != null){
                evento.setNombre(eventoDTO.getNombre());
            }
            if(eventoDTO.getTimestamp() != null){
                evento.setTimestamp(eventoDTO.getTimestamp());
            }
            if(eventoDTO.getLugar() != null){
                evento.setLugar(eventoDTO.getLugar());
                UbicacionDTO ubicacionDTO = mapaService.getCoordenadas(eventoDTO.getLugar());
                evento.setLat(ubicacionDTO.getLat());
                evento.setLon(ubicacionDTO.getLon());
            }
            if(eventoDTO.getImagen() != null){
                evento.setImagen(eventoDTO.getImagen());
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
