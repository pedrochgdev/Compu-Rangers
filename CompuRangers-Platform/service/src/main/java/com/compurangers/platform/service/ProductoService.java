package com.compurangers.platform.service;

import com.compurangers.platform.core.domain.catalog.Producto;
import java.util.List;
import com.compurangers.platform.dao.catalog.IProductoDAO;

public class ProductoService {
    private final IProductoDAO productDAO;

    public ProductoService(IProductoDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Producto> getAllProducts() {
        return productDAO.getAll();
    }
}