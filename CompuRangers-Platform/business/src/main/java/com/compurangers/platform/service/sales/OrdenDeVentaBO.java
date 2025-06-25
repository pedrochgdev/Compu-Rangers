package com.compurangers.platform.service.sales;

import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import com.compurangers.platform.dao.sales.IOrdenDeVentaDAO;
import java.util.List;

public class OrdenDeVentaBO {
    private final IOrdenDeVentaDAO ordenDeVentaDAO;

    public OrdenDeVentaBO(IOrdenDeVentaDAO ordenDeVentaDAO) {
        this.ordenDeVentaDAO = ordenDeVentaDAO;
    }

    public int addOrdenDeVenta(OrdenDeVenta orden) {
        return ordenDeVentaDAO.add(orden);
    }

    public boolean updateOrdenDeVenta(OrdenDeVenta orden) {
        return ordenDeVentaDAO.update(orden);
    }

    public boolean deleteOrdenDeVenta(int id) {
        return ordenDeVentaDAO.delete(id);
    }

    public OrdenDeVenta searchOrdenDeVenta(int id) {
        return ordenDeVentaDAO.search(id);
    }

    public List<OrdenDeVenta> getAllOrdenDeVenta() {
        return ordenDeVentaDAO.getAll();
    }
    
    public List<OrdenDeVenta> getOrdenesFromUsuario(int userid) {
        return ordenDeVentaDAO.getAllByForeignKey(userid);
    }
    
    public double getTotalHistorico(){
        return ordenDeVentaDAO.getTotalHistorico();
    }
    
    public int getPedidosHoy(){
        return ordenDeVentaDAO.getPedidosHoy();
    }
    
}
