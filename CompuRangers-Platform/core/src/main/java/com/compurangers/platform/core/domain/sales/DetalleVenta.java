package com.compurangers.platform.core.domain.sales;

import com.compurangers.platform.core.domain.catalog.Producto;

public class DetalleVenta {
    private int id;
    private int cantidad;
    private double subtotal;
    private int devuelto;
    private Producto producto;
    private int idOrdenVenta; 
    
    public DetalleVenta(){}

    public DetalleVenta(int idOrdenVenta, Producto producto, int cantidad){
        this.idOrdenVenta=idOrdenVenta;
        this.producto=producto;
        this.cantidad=cantidad;
        this.subtotal=producto.getPrecioVenta()*cantidad;
        this.devuelto=0;
    }
    
    public DetalleVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = producto.getPrecioVenta() * cantidad;
        this.devuelto = 0;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getDevuelto() {
        return devuelto;
    }

    public void setDevuelto(int devuelto) {
        this.devuelto = devuelto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto prod) {
        this.producto = prod;
    }
    
    public int getIdOrdenVenta() {
        return idOrdenVenta;
    }

    public void setIdOrdenVenta(int id) {
        this.idOrdenVenta = id;
    }
    
    @Override
    public String toString() {
        return "DetalleVenta{id=" + id + ", cantidad=" + cantidad + ", precioProducto=" + subtotal +
               ", cantidadDevuelta=" + devuelto + ", productoId=" + producto.getId() + "}";
    }
    
}
