package com.ingweb.backend.service;

import com.ingweb.backend.dao.EventoRepository;
import com.ingweb.backend.model.dto.EventoDTO;
import com.ingweb.backend.model.dto.UbicacionDTO;
import com.ingweb.backend.model.entity.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private MapaService mapaService;

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

    public EventoDTO modificarEvento(String nombreEvento, EventoDTO eventoDTO){
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
            if(eventoDTO.getOrganizador() != null){
                evento.setOrganizador(eventoDTO.getOrganizador());
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

    public void borrarEvento(String nombreEvento){
        Evento evento = eventoRepository.findByNombre(nombreEvento);
        if(evento != null){
            eventoRepository.delete(evento);
        }else {
            throw new IllegalArgumentException("Evento no encontrado");
        }
    }
}
