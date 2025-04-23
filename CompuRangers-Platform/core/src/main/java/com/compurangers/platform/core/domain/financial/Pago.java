package com.compurangers.platform.core.domain.financial;

import java.util.Date;

public class Pago {
    private int id;
    private double monto;
    private Date fechaPago;
    private String estado;
    private String referencia;

    private int documentoDeVentasNumero;
    private int documentoDeComprasNumero;
    private int metodoDePagoId;

    private int monedaId;
    private int periodoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getDocumentoDeVentasNumero() {
        return documentoDeVentasNumero;
    }

    public void setDocumentoDeVentasNumero(int documentoDeVentasNumero) {
        this.documentoDeVentasNumero = documentoDeVentasNumero;
    }
    
    public int getDocumentoDeComprasNumero() {
        return documentoDeComprasNumero;
    }

    public void setDocumentoDeComprasNumero(int documentoDeComprasNumero) {
        this.documentoDeComprasNumero = documentoDeComprasNumero;
    }

    public int getMetodoDePagoId() {
        return metodoDePagoId;
    }

    public void setMetodoDePagoId(int metodoDePagoId) {
        this.metodoDePagoId = metodoDePagoId;
    }

    public int getMonedaId() {
        return monedaId;
    }

    public void setMonedaId(int monedaId) {
        this.monedaId = monedaId;
    }

    public int getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(int periodoId) {
        this.periodoId = periodoId;
    }
    
}
