package com.compurangers.platform.core.domain.sales.dto;

import java.util.Date;

public class PedidoPorDiaDTO {
    private Date dia;
    private int cantidad;

    public PedidoPorDiaDTO() {}

    public PedidoPorDiaDTO(Date dia, int cantidad) {
        this.dia = dia;
        this.cantidad = cantidad;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

