package com.ingweb.backend.dao;

import com.ingweb.backend.model.entity.Evento;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventoRepository extends MongoRepository<Evento, ObjectId> {
    Evento findByNombre(String nombre);
}
