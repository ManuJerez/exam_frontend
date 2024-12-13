package com.ingweb.backend.model.dto;
import java.util.List;

public class UsuarioDTO {
    private String id;
    private String uid;
    private String email;
    private List<LugarDTO> lugares;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
