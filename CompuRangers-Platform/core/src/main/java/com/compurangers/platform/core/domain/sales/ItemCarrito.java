package com.compurangers.platform.core.domain.sales;

import com.compurangers.platform.core.domain.catalog.Producto;

public class ItemCarrito {
    
    private Producto producto;
    private int cantidad;

    public ItemCarrito() {}

    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public double getSubtotal() {
        return producto.getPrecioVenta() * cantidad;
    }
    
}
