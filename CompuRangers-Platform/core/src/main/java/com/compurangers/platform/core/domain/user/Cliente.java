package com.compurangers.platform.core.domain.user;

import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private final List<OrdenDeVenta> historialCompras;
    private String direccionPreferida;
    
    public Cliente() { this.historialCompras = new ArrayList<>(); }
    
    public Cliente(int id, String username, String nombreCompleto, String correoElectronico, 
                  String telefono, String direccion, String contrasena, Timestamp created, Timestamp updated){
        super(id, username, nombreCompleto, correoElectronico, telefono, direccion, contrasena, created, updated);
        this.historialCompras = new ArrayList<>();
    }

    public List<OrdenDeVenta> getHistorialCompras() {
        return historialCompras;
    }

    public void agregarCompra(OrdenDeVenta compra) {
        historialCompras.add(compra);
    }
    
    public String getDireccionPreferida() {
        return direccionPreferida;
    }

    public void setDireccionPreferida(String direccionPreferida) {
        this.direccionPreferida = direccionPreferida;
    }

    @Override
    public String obtenerRol() {
        return "Cliente";
    }

}

