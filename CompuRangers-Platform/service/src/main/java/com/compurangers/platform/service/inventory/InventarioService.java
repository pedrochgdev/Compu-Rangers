package com.compurangers.platform.service.inventory;

import com.compurangers.platform.core.domain.inventory.Inventario;
import com.compurangers.platform.dao.inventory.IInventarioDAO;
import java.util.List;

public class InventarioService {
    private final IInventarioDAO inventarioDAO;

    public InventarioService(IInventarioDAO inventarioDAO) {
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
}
