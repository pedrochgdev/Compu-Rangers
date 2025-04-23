package com.compurangers.platform.core.domain.sales;

import com.compurangers.platform.core.domain.catalog.Producto;

public class ItemCarrito {
    
    private int id;
    private Producto producto;
    private int cantidad;
    private int carritoId;
    private double subtotal;
    
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
        return subtotal;
    }
    public void setSubtotal(double subtotal){
        this.subtotal= subtotal;
    }
    public int getCarritoId(){
        return carritoId;
    }
    public void setCarritoId(int c_id){
        this.carritoId = c_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "ItemCarrito{id=" + id + ", cantidad=" + cantidad + ", subtotal=" + subtotal +
               ", carritoId=" + carritoId + ", productoId=" + (producto != null ? producto.getId() : "null") + "}";
    }
    
}
