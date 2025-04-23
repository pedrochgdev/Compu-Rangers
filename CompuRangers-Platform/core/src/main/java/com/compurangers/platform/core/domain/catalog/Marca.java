package com.compurangers.platform.core.domain.catalog;

public class Marca {
    private int id;
    private String nombre;

    // Constructor vacío
    public Marca() {}

    // Constructor completo
    public Marca(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    @Override
    public String toString() {
        return "Marca{id=" + id + ", nombre='" + nombre + "'}";
    }
    
}

