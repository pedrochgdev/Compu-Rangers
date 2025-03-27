package com.aprilboutique;

import com.aprilboutique.services.ProductService;
import com.aprilboutique.models.Product;
import java.util.List;

public class BackendJavaAnt {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
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