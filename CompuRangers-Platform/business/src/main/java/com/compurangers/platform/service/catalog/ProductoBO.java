package com.compurangers.platform.service.catalog;

import com.compurangers.platform.core.domain.catalog.Producto;
import java.util.List;
import com.compurangers.platform.dao.catalog.IProductoDAO;

public class ProductoBO {
    private final IProductoDAO productDAO;

    public ProductoBO(IProductoDAO productDAO) {
        this.productDAO = productDAO;
    }
    
    public int addProducto(Producto modelo) {
        return productDAO.add(modelo);
    }

    public boolean updateProducto(Producto modelo) {
        return productDAO.update(modelo);
    }

    public boolean deleteProducto(int id) {
        return productDAO.delete(id);
    }

    public Producto searchProducto(int id) {
        return productDAO.search(id);
    }
    
    public List<Producto> getAllProductos() {
        return productDAO.getAll();
    }
    
}
