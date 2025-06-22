package com.compurangers.platform.dao.sales;

import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import com.compurangers.platform.dao.ICrud;

public interface IOrdenDeVentaDAO extends ICrud<OrdenDeVenta>{
    double getTotalHistorico();
    int getPedidosHoy();
}
