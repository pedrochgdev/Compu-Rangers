package com.compurangers.platform.dao;

import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import java.util.List;

public interface IOrdenDeVentaDAO {
    int addSale(OrdenDeVenta sale);
    int updateSale(OrdenDeVenta sale);
    int deleteSale(int id);
    List<OrdenDeVenta> getAllSales();
}
