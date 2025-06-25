package com.compurangers.platform.dao.sales;

import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import com.compurangers.platform.dao.IListByFk;

public interface IOrdenDeVentaDAO extends IListByFk<OrdenDeVenta>{
    double getTotalHistorico();
    int getPedidosHoy();
}
