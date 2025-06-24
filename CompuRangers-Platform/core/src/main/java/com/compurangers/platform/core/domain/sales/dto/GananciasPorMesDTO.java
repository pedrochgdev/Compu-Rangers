package com.compurangers.platform.core.domain.sales.dto;

import java.math.BigDecimal;
import java.util.Date;

public class GananciasPorMesDTO {
    private Date mes;
    private BigDecimal ganancia; 

    public GananciasPorMesDTO() {}

    public GananciasPorMesDTO(Date mes, BigDecimal ganancia) {
        this.mes = mes;
        this.ganancia = ganancia;
    }

    public Date getMes() {
        return mes;
    }

    public void setMes(Date mes) {
        this.mes = mes;
    }

    public BigDecimal getGanancia() {
        return ganancia;
    }

    public void setGanancia(BigDecimal ganancia) {
        this.ganancia = ganancia;
    }

    @Override
    public String toString() {
        return "GananciaMensualDTO{" +
                "mes=" + mes +
                ", ganancia=" + ganancia +
                '}';
    }
}
