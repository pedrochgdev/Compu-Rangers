package com.compurangers.platform.core.domain.configuration;

public class ImpuestoPeriodo {
    private int periodoId;
    private int impuestoId;
    private double tasa;
    private String estado;

    public int getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(int periodoId) {
        this.periodoId = periodoId;
    }

    public int getImpuestoId() {
        return impuestoId;
    }

    public void setImpuestoId(int impuestoId) {
        this.impuestoId = impuestoId;
    }

    public double getTasa() {
        return tasa;
    }

    public void setTasa(double tasa) {
        this.tasa = tasa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
