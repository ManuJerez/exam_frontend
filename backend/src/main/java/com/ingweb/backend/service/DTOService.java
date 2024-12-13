package com.ingweb.backend.service;

import com.ingweb.backend.model.dto.DTO;

import java.util.ArrayList;
import java.util.List;

public abstract class DTOService<DTOClass, EntityClass> {
    protected List<DTOClass> entidadesADTO (List<EntityClass> entidades) {
        List<DTOClass> lista = new ArrayList<>();
        for (EntityClass entidad : entidades) {
            DTO<DTOClass> clase = (DTO<DTOClass>) entidad;
            lista.add(clase.toDto());
        }
        return lista;
    }
}