package com.ingweb.backend.model.entity;

import com.ingweb.backend.model.dto.DTO;
import com.ingweb.backend.model.dto.LugarDTO;
import com.ingweb.backend.model.dto.UsuarioDTO;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "Usuario")
public class Usuario implements DTO<UsuarioDTO> {
    @Id
    private ObjectId id;

    @Field("uid")
    private String uid;
    @Field("email")
    private String email;
    @Field("lugares")
    private List<LugarDTO> lugares;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<LugarDTO> getLugares() {
        return lugares;
    }

    public void setLugares(List<LugarDTO> lugares) {
        this.lugares = lugares;
    }

    @Override
    public UsuarioDTO toDto() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        String idString = this.id.toHexString();
        usuarioDTO.setId(idString);
        usuarioDTO.setUid(this.uid);
        usuarioDTO.setEmail(this.email);
        usuarioDTO.setLugares(this.lugares);

        return usuarioDTO;
    }
}
