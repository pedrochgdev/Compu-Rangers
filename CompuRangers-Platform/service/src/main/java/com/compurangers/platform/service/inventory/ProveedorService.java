package com.compurangers.platform.service.inventory;

import com.compurangers.platform.core.domain.inventory.Proveedor;
import com.compurangers.platform.dao.inventory.IProveedorDAO;
import java.util.List;

public class ProveedorService {
    private final IProveedorDAO proveedrDAO;

    public ProveedorService(IProveedorDAO proveedrDAO) {
        this.proveedrDAO = proveedrDAO;
    }

    public int addProveedor(Proveedor lote) {
        return proveedrDAO.add(lote);
    }

    public boolean updateProveedor(Proveedor lote) {
        return proveedrDAO.update(lote);
    }

    public boolean deleteProveedor(int id) {
        return proveedrDAO.delete(id);
    }

    public Proveedor searchProveedor(int id) {
        return proveedrDAO.search(id);
    }

    public List<Proveedor> getAllProveedores() {
        return proveedrDAO.getAll();
    }
    
}
