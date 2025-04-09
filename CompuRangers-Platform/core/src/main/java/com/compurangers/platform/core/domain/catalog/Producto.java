package com.compurangers.platform.core.domain.catalog;

public class Producto {
    private int id;
    private String sku;
    private String nombre;
    private String descripcion;
    private double precioVenta;
    private int categoriaId;
    private int marcaId;

    // Constructor vacío
    public Producto() {}

    // Constructor completo
    public Producto(int id, String sku, String nombre, String descripcion, 
                    double precioVenta, int categoriaId, int marcaId) {
        this.id = id;
        this.sku = sku;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.categoriaId = categoriaId;
        this.marcaId = marcaId;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }

    public int getCategoriaId() { return categoriaId; }
    public void setCategoriaId(int categoriaId) { this.categoriaId = categoriaId; }

    public int getMarcaId() { return marcaId; }
    public void setMarcaId(int marcaId) { this.marcaId = marcaId; }
}
