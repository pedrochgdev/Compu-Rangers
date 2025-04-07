package com.compurangers.platform.service;

import com.compurangers.platform.core.domain.Product;
import com.compurangers.platform.dao.IProductDAO;
import java.util.List;

public class ProductService {
    private final IProductDAO productDAO;

    public ProductService(IProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }
}