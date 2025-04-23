package com.compurangers.platform.core.domain.sales;

import java.util.Date;

public class OrdenDeDevolucion {
    private int id;
    private String motivo;
    private Date fechaRegistro;
    private String tipoDevolucion;
    private int documentoDeVentasNumero;

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTipoDevolucion() {
        return tipoDevolucion;
    }

    public void setTipoDevolucion(String tipoDevolucion) {
        this.tipoDevolucion = tipoDevolucion;
    }

    public int getDocumentoDeVentasNumero() {
        return documentoDeVentasNumero;
    }

    public void setDocumentoDeVentasNumero(int documentoDeVentasNumero) {
        this.documentoDeVentasNumero = documentoDeVentasNumero;
    }
}

