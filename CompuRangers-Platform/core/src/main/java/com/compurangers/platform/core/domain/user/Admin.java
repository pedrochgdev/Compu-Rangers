package com.compurangers.platform.core.domain.user;

import java.sql.Timestamp;
import java.util.Date;

public class Admin extends Usuario {
    private Date fechaIngreso;
    
    public Admin() {}
    
    public Admin(int id, String username, String nombreCompleto, String correoElectronico, 
                 String telefono, String direccion, String contrasena, 
                 Date fechaIngreso, Timestamp created, Timestamp updated) {
        super(id, username, nombreCompleto, correoElectronico, telefono, direccion, contrasena, created, updated);
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String obtenerRol() {
        return "Administrador";
    }
}

