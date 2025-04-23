/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.compurangers.platform.core.domain.catalog;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private int id;
    private String nombre;
    private String descripcion;
    private Categoria categoriaPadre;
    private List<Categoria> subcategorias = new ArrayList<>();

    // Constructor vacío
    public Categoria() {}

    // Constructor básico
    public Categoria(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Constructor con padre
    public Categoria(int id, String nombre, String descripcion, Categoria categoriaPadre) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaPadre = categoriaPadre;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Categoria getCategoriaPadre() { return categoriaPadre; }
    public void setCategoriaPadre(Categoria categoriaPadre) { 
        this.categoriaPadre = categoriaPadre;
    }

    public List<Categoria> getSubcategorias() { return subcategorias; }
    public void agregarSubcategoria(Categoria subcategoria) {
        subcategoria.setCategoriaPadre(this);
        this.subcategorias.add(subcategoria);
    }
    
    @Override
    public String toString() {
        return "Categoria{id=" + id + ", nombre='" + nombre + "'}";
    }
    
}

