package com.compurangers.platform.core.domain.user;

import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private final List<OrdenDeVenta> historialCompras;
    private String metodoPagoPreferido;

    public Cliente(String id, String nombreCompleto, String correoElectronico, String telefono,
                   String direccion, String contrasena) {
        super(id, nombreCompleto, correoElectronico, telefono, direccion, contrasena);
        this.historialCompras = new ArrayList<>();
        this.metodoPagoPreferido = "No definido";
    }

    public List<OrdenDeVenta> getHistorialCompras() {
        return historialCompras;
    }

    public void agregarCompra(OrdenDeVenta compra) {
        historialCompras.add(compra);
    }

    public String getMetodoPagoPreferido() {
        return metodoPagoPreferido;
    }

    public void setMetodoPagoPreferido(String metodoPagoPreferido) {
        this.metodoPagoPreferido = metodoPagoPreferido;
    }

    @Override
    public String obtenerRol() {
        return "Cliente";
    }
}

