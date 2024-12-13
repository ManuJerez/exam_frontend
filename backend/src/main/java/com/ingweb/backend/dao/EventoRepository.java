package com.ingweb.backend.dao;

import com.ingweb.backend.model.entity.Evento;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventoRepository extends MongoRepository<Evento, ObjectId> {
    Evento findByNombre(String nombre);
    List<Evento> findByOrganizador(String organizador);
    List<Evento> findByLatAndLonOrderByTimestamp(Double lat, Double lon);
}
