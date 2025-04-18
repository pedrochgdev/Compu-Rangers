package com.compurangers.platform.core.domain.inventory;

public class DetalleLote {
    private int id;
    private int cantidad;
    private double precioCompra;
    private int loteId;
    private int productoId;

    public DetalleLote(int id, int cantidad, double precioCompra, int loteId, int productoId) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.loteId = loteId;
        this.productoId = productoId;
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

    public int getProductoId() { return productoId; }
    public void setProductoId(int productoId) { this.productoId = productoId; }
}
