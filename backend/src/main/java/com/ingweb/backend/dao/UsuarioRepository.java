package com.ingweb.backend.dao;

import com.ingweb.backend.model.entity.Usuario;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, ObjectId> {
    Usuario findByEmailIgnoreCase(String email);
    Usuario findByUid(String uid);
}
