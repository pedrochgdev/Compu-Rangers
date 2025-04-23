package com.compurangers.platform.core.domain.sales;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

    private int id;
    private List<ItemCarrito> items;
    private double total;
    private int cantidad_productos;
    private int usuario_id;

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

    public int getCantidad_productos() {
        return cantidad_productos;
    }

    public void setCantidad_productos(int cantidad_productos) {
        this.cantidad_productos = cantidad_productos;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

}
