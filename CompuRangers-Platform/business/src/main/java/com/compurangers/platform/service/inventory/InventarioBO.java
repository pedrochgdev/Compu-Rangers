package com.compurangers.platform.service.inventory;

import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.core.domain.inventory.Inventario;
import com.compurangers.platform.dao.inventory.IInventarioDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventarioBO {
    private final IInventarioDAO inventarioDAO;

    public InventarioBO(IInventarioDAO inventarioDAO) {
        this.inventarioDAO = inventarioDAO;
    }

    public int addInventario(Inventario inventario) {
        return inventarioDAO.add(inventario);
    }

    public boolean updateInventario(Inventario inventario) {
        return inventarioDAO.update(inventario);
    }

    public boolean deleteInventario(int id) {
        return inventarioDAO.delete(id);
    }

    public Inventario searchInventario(int id) {
        return inventarioDAO.search(id);
    }

    public List<Inventario> getAllInventarios() {
        return inventarioDAO.getAll();
    }
    
    public Map<Producto, Integer> getCatalog(){
        Map<Producto, Integer> totalPorProducto = new HashMap<>();
    
    for (Inventario inv : getAllInventarios()) {
        Producto producto = inv.getProducto();
        int cantidad = inv.getCantidadDisponible();

        totalPorProducto.merge(producto, cantidad, Integer::sum);
    }

    return totalPorProducto;
    }
    
}
