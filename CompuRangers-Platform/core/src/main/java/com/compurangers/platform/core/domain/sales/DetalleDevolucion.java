package com.compurangers.platform.core.domain.sales;

public class DetalleDevolucion {
    private int id;
    private int cantidad; 
    private int detalleVentaId;
    private int ordenDevolucionId;

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

    public int getDetalleVentaId() {
        return detalleVentaId;
    }

    public void setDetalleVentaId(int detalleVentaId) {
        this.detalleVentaId = detalleVentaId;
    }

    public int getOrdenDevolucionId() {
        return ordenDevolucionId;
    }

    public void setOrdenDevolucionId(int ordenDevolucionId) {
        this.ordenDevolucionId = ordenDevolucionId;
    }
}
