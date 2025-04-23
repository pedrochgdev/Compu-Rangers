package com.compurangers.platform.core.domain.configuration;

public class MonedaPeriodo {
    private int monedaId;
    private int periodoId;
    private double valor;
    private char tipoCambio;
    private String estado;

    public int getMonedaId() {
        return monedaId;
    }

    public void setMonedaId(int monedaId) {
        this.monedaId = monedaId;
    }

    public char getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(char tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public int getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(int periodoId) {
        this.periodoId = periodoId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}

