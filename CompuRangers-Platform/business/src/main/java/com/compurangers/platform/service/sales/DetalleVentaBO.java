package com.compurangers.platform.service.sales;

import com.compurangers.platform.core.domain.sales.DetalleVenta;
import com.compurangers.platform.dao.sales.IDetalleVentaDAO;
import java.util.List;

public class DetalleVentaBO {
    private final IDetalleVentaDAO detalleVentaDAO;

    public DetalleVentaBO(IDetalleVentaDAO detalleVentaDAO) {
        this.detalleVentaDAO = detalleVentaDAO;
    }

    public int addDetalleVenta(DetalleVenta detalleVenta) {
        return detalleVentaDAO.add(detalleVenta);
    }

    public boolean updateDetalleVenta(DetalleVenta detalleVenta) {
        return detalleVentaDAO.update(detalleVenta);
    }

    public boolean deleteDetalleVenta(int id) {
        return detalleVentaDAO.delete(id);
    }

    public DetalleVenta searchDetalleVenta(int id) {
        return detalleVentaDAO.search(id);
    }

    public List<DetalleVenta> getAllDetalleVenta() {
        return detalleVentaDAO.getAll();
    }
    
    public List<DetalleVenta> getAllDetalleVentaFromOrder(int id) {
        return detalleVentaDAO.getAllByForeignKey(id);
    }
}
