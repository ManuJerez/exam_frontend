package com.ingweb.backend.service;

import com.ingweb.backend.dao.UsuarioRepository;
import com.ingweb.backend.model.dto.LugarDTO;
import com.ingweb.backend.model.dto.UbicacionDTO;
import com.ingweb.backend.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MapaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UbicacionDTO getCoordenadas(String direccion){
        RestTemplate restTemplate = new RestTemplate();
        String urlAPIMapa = "https://nominatim.openstreetmap.org/search?format=json&limit=1&q=" + direccion;
        ResponseEntity<List<UbicacionDTO>> response = restTemplate.exchange(
                urlAPIMapa,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody().get(0);
    }

    public void anyadirUbicacion(String usuarioEmail, String direccion){
        UbicacionDTO ubicacionDTO = getCoordenadas(direccion);
        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(usuarioEmail);

        LugarDTO lugarDTO = new LugarDTO();
        lugarDTO.setNombre(direccion);
        lugarDTO.setLat(ubicacionDTO.getLat());
        lugarDTO.setLon(ubicacionDTO.getLon());

        usuario.getLugares().add(lugarDTO);
        usuarioRepository.save(usuario);
    }
}
