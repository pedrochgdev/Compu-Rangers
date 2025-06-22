package com.compurangers.platform.dao.sales;

import com.compurangers.platform.core.domain.sales.ItemCarrito;
import com.compurangers.platform.dao.IListByFk;

public interface IItemCarritoDAO extends IListByFk<ItemCarrito>{
    ItemCarrito searchItem(int carritoId, int productoId);
    boolean deleteAllByCarritoId(int cid);
}
