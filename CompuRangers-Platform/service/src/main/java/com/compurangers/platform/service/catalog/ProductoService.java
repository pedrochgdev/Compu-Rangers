package com.compurangers.platform.service.catalog;

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

    public int addProducto(Producto producto) {
        return productDAO.add(producto);
    }

    public boolean updateProducto(Producto producto) {
        return productDAO.update(producto);
    }

    public boolean deleteProducto(int id) {
        return productDAO.delete(id);
    }

    public Producto searchProducto(int id) {
        return productDAO.search(id);
    }
}
