package com.compurangers.platform.core.domain.sales;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    
    private List<ItemCarrito> items;
    private double total;

    public Carrito() {
        this.items = new ArrayList<>();
    }

    public List<ItemCarrito> getItems() {
        return items;
    }
  
    public double getTotal() {
        for (ItemCarrito item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

}
