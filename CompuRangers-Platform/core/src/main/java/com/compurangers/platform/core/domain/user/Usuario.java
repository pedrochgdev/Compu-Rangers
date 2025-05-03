package com.compurangers.platform.core.domain.user;

import java.sql.Timestamp;

public abstract class Usuario {
    private int id;
    private String username;
    private String nombreCompleto;
    private String correoElectronico;
    private String telefono; 
    private String direccion;
    private String contrasena;
    private Timestamp created;
    private Timestamp updated;

    public Usuario() {}
    
    public Usuario(int id, String username, String nombreCompleto, String correoElectronico, 
                  String telefono, String direccion, String contrasena, 
                  Timestamp created, Timestamp updated) {
        this.id = id;
        this.username = username;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.direccion = direccion;
        this.contrasena = contrasena;
        this.created = created;
        this.updated = updated;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public abstract String obtenerRol();
}

