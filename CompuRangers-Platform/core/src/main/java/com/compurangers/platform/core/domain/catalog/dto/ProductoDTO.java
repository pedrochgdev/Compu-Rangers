package com.compurangers.platform.core.domain.catalog.dto;

import com.compurangers.platform.core.domain.catalog.Producto;

public class ProductoDTO {
    private Producto producto;
    private int cantidadDisponible;

    public ProductoDTO() {}

    public ProductoDTO(Producto producto, int cantidadDisponible) {
        this.producto = producto;
        this.cantidadDisponible = cantidadDisponible;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
}

