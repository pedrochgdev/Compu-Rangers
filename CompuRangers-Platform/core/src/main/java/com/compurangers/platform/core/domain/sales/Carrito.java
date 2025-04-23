package com.compurangers.platform.core.domain.sales;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

    private int id;
    private List<ItemCarrito> items;
    private double total;
    private int cantidadProductos;
    private int usuarioId;

    public Carrito() {
        this.items = new ArrayList<>();
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        total = 0;
        for (ItemCarrito item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(int cantidad_productos) {
        this.cantidadProductos = cantidad_productos;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuario_id) {
        this.usuarioId = usuario_id;
    }
    
    @Override
    public String toString() {
        return "Carrito{id=" + id + ", total=" + total + ", items=" + items + "}";
    }

}
