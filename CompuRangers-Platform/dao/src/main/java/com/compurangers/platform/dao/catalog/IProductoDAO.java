package com.compurangers.platform.dao.catalog;

import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.dao.ICrud;
import java.util.List;

public interface IProductoDAO extends ICrud<Producto>{
    List<Producto> getRanking();
    List<Producto> searchAvanzado(String nombre, Integer marcaId, Integer categoriaId);
}