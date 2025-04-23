package com.compurangers.platform.core.domain.sales;

public class DocumentoDeVentas {
    private int numero;
    private double subtotal;
    private double impuestos;
    private double total;
    private double totalPagado;
    private double totalPendiente;
    private int ordenDeVentaId; 

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

    public double getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(double totalPagado) {
        this.totalPagado = totalPagado;
    }

    public double getTotalPendiente() {
        return totalPendiente;
    }

    public void setTotalPendiente(double totalPendiente) {
        this.totalPendiente = totalPendiente;
    }

    public int getOrdenDeVentaId() {
        return ordenDeVentaId;
    }

    public void setOrdenDeVentaId(int ordenDeVentaId) {
        this.ordenDeVentaId = ordenDeVentaId;
    }
    
}

