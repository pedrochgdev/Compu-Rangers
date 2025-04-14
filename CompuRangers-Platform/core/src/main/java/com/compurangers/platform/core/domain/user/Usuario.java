package com.compurangers.platform.core.domain.user;

public abstract class Usuario {
    private String id; // ID único del usuario
    private String nombreCompleto; // PII
    private String correoElectronico; // PII
    private String telefono; // PII
    private String direccion; // PII
    private String contrasena; // Debería almacenarse encriptada

    public Usuario(String id, String nombreCompleto, String correoElectronico, String telefono, String direccion, String contrasena) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.direccion = direccion;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public String getId() {
        return id;
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

    // Método abstracto para que cada tipo de usuario implemente su rol
    public abstract String obtenerRol();
}

