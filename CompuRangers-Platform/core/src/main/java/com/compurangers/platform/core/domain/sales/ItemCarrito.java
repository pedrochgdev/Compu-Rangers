package com.compurangers.platform.core.domain.sales;

import com.compurangers.platform.core.domain.catalog.Producto;

public class ItemCarrito {
    
    private Producto producto;
    private int cantidad;
    private int carrito_id;
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
        return producto.getPrecioVenta() * cantidad;
    }
    public void setSubtotal(double subtotal){
        this.subtotal= subtotal;
    }
    public int getCarrito_id(){
        return carrito_id;
    }
    public void setCarrito_id(int c_id){
        this.carrito_id = c_id;
    }
}
