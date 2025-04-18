package com.compurangers.platform.core.domain.sales;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdenDeVenta {
    private int id;
    private Date fecha;
    private char estado;
    private double total;
    private List<DetalleVenta> detalles;

    public OrdenDeVenta() {
        this.fecha = new Date();
        this.estado = 'C'; // Estado por defecto
        this.detalles = new ArrayList<>();
        this.total = 0.0;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }
    
    public void cargarListaDetalle(List<ItemCarrito> carrito) {

        for (ItemCarrito item : carrito) {
            DetalleVenta detalle = new DetalleVenta(item.getProducto(), item.getCantidad());
            this.detalles.add(detalle);
            this.total += detalle.getPrecio();
        }

    }
    
}
