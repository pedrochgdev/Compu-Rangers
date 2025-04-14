package com.compurangers.platform.core.domain.user;

import java.time.LocalDate;

public class Admin extends Usuario {
    private LocalDate fechaIngreso;

    public Admin(String id, String nombreCompleto, String correoElectronico, String telefono,
                 String direccion, String contrasena, LocalDate fechaIngreso) {
        super(id, nombreCompleto, correoElectronico, telefono, direccion, contrasena);
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String obtenerRol() {
        return "Administrador";
    }
}

