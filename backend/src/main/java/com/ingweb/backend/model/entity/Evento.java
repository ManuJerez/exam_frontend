package com.ingweb.backend.model.entity;

import com.ingweb.backend.model.dto.DTO;
import com.ingweb.backend.model.dto.EventoDTO;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "Evento")
public class Evento implements DTO<EventoDTO> {
    @Id
    private ObjectId id;
    @Field("nombre")
    private String nombre;
    @Field("timestamp")
    private LocalDateTime timestamp;
    @Field("lugar")
    private String lugar;
    @Field("lat")
    private double lat;
    @Field("lon")
    private double lon;
    @Field("organizador")
    private String organizador;
    @Field("imagen")
    private String imagen;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public EventoDTO toDto() {
        EventoDTO eventoDTO = new EventoDTO();
        eventoDTO.setNombre(this.nombre);
        eventoDTO.setTimestamp(this.timestamp);
        eventoDTO.setLugar(this.lugar);
        eventoDTO.setLat(this.lat);
        eventoDTO.setLon(this.lon);
        eventoDTO.setOrganizador(this.organizador);
        eventoDTO.setImagen(this.imagen);

        return eventoDTO;
    }
}
