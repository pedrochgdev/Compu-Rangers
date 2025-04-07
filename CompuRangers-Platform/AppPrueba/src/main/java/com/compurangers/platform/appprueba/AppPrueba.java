package com.compurangers.platform.appprueba;

import com.compurangers.platform.service.ProductService;
import com.compurangers.platform.core.domain.Product;
import com.compurangers.platform.dao.mysql.ProductDAOImpl;
import java.util.List;

public class AppPrueba {
    public static void main(String[] args) {
        ProductService productService = new ProductService(new ProductDAOImpl());
        List<Product> products = productService.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No se encontraron productos o hubo un error en la conexión.");
        } else {
            System.out.println("¡Conexión exitosa a la base de datos! Productos encontrados:");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }
}