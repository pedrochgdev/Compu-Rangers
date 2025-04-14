package com.compurangers.platform.dao;

import com.compurangers.platform.core.domain.catalog.Producto;
import java.util.List;

public interface IProductoDAO {
    int addProduct(Producto product);
    int updateProduct(Producto product);
    int deleteProduct(int id);
    List<Producto> getAllProducts();
}