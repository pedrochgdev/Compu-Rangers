package com.compurangers.platform.core.domain.inventory;

public class Proveedor {
    private int id;
    private String razonSocial;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    
    @Override
    public String toString() {
        return "Proveedor{id=" + id + ", razonSocial='" + razonSocial + "'}";
    }
    
}

