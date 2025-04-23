package com.compurangers.platform.core.domain.inventory;

import com.compurangers.platform.core.domain.catalog.Producto;

public class DetalleLote {
    private int id;
    private int cantidad;
    private double precioCompra;
    private int loteId;
    private Producto producto;
    
    public DetalleLote() {}
    
    public DetalleLote(int id, int cantidad, double precioCompra, int loteId, Producto producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.loteId = loteId;
        this.producto = producto;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecioCompra() { return precioCompra; }
    public void setPrecioCompra(double precioCompra) { this.precioCompra = precioCompra; }

    public int getLoteId() { return loteId; }
    public void setLoteId(int loteId) { this.loteId = loteId; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
}
