
package com.aprilboutique.services;

import com.aprilboutique.models.Product;
import com.aprilboutique.repositories.ProductRepository;
import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }
}