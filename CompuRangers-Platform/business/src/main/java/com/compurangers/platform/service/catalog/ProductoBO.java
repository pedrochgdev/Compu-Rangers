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
    
    public List<Producto> getRanking(){
        return productDAO.getRanking();
    }
    public List<Producto> getSearchAvanzadoProductos(String nombre,Integer categoryId,Integer marcaId){
        Integer catId = categoryId > 0 ? categoryId : null;
        Integer marId = marcaId > 0 ? marcaId : null;
        String filtroNombre = (nombre != null && !nombre.trim().isEmpty()) ? nombre : null;
        return productDAO.searchAvanzado(filtroNombre, catId, marId);
    }
    
}
