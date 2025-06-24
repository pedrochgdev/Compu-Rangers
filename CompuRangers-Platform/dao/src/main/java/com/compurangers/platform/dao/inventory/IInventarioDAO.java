package com.compurangers.platform.dao.inventory;

import com.compurangers.platform.core.domain.inventory.Inventario;
import com.compurangers.platform.dao.ICrud;
import java.util.List;

public interface IInventarioDAO extends ICrud<Inventario>{
    List<Inventario> getInvDisponible(int productoId);
    List<Inventario> getInvReponer(int productoId);
    int getCantidadTotalDisponible(int productoId);
}
