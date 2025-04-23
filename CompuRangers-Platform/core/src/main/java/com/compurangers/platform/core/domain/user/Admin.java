package com.compurangers.platform.core.domain.user;

import java.util.Date;

public class Admin extends Usuario {
    private Date fechaIngreso;

    public Admin(String id, String nombreCompleto, String correoElectronico, String telefono,
                 String direccion, String contrasena, Date fechaIngreso) {
        super(id, nombreCompleto, correoElectronico, telefono, direccion, contrasena);
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

