package com.compurangers.platform.core.domain.catalog;

public class Marca {
    private int id;
    private String nombre;
    private String descripcion;

    // Constructor vacío
    public Marca() {}

    // Constructor completo
    public Marca(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}

