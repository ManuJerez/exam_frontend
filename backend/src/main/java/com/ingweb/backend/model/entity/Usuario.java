package com.ingweb.backend.model.entity;

import com.ingweb.backend.model.dto.DTO;
import com.ingweb.backend.model.dto.UsuarioDTO;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Usuario")
public class Usuario implements DTO<UsuarioDTO> {
    @Id
    private ObjectId id;

    @Field("uid")
    private String uid;
    @Field("nombre")
    private String nombre;
    @Field("email")
    private String email;
    @Field("contrasenya")
    private String contrasenya;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @Override
    public UsuarioDTO toDto() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(this.id);
        usuarioDTO.setUid(this.uid);
        usuarioDTO.setNombre(this.nombre);
        usuarioDTO.setEmail(this.email);
        usuarioDTO.setContrasenya(this.contrasenya);

        return usuarioDTO;
    }
}
