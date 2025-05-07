package com.compurangers.platform.core.domain.sales;

public class DocumentoDeVentas {
    private int id;
    private int numero;
    private double subtotal;
    private double impuestos;
    private double total;
    private int ordenDeVentaId; 
    private String direccionEnvio;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getOrdenDeVentaId() {
        return ordenDeVentaId;
    }

    public void setOrdenDeVentaId(int ordenDeVentaId) {
        this.ordenDeVentaId = ordenDeVentaId;
    }
    
    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }
    
}

