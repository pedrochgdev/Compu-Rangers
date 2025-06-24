package com.compurangers.platform.core.domain.sales;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdenDeVenta {
    private int id;
    private Date fecha;
    private String estado;
    private double total;
    private List<DetalleVenta> detalles;
    private int clienteId;
    private String direccion;
    
    public OrdenDeVenta() {
        this.fecha = new Date();
        this.estado = "PROCESADO"; // Estado por defecto
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
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
        
    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    @Override
    public String toString() {
        return "OrdenDeVenta{id=" + id + ", fecha=" + fecha + ", estado=" + estado +
               ", total=" + total + ", detalles=" + detalles + "}";
    }
    
}
