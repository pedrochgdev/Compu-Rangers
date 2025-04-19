package com.compurangers.platform.appprueba;

import com.compurangers.platform.service.catalog.ProductoService;
import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.dao.mysql.catalog.ProductoDAOImpl;
import java.util.List;

public class AppPrueba {
    public static void main(String[] args) {
        ProductoService productService = new ProductoService(new ProductoDAOImpl());
        List<Producto> products = productService.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No se encontraron productos o hubo un error en la conexión.");
        } else {
            System.out.println("¡Conexión exitosa a la base de datos! Productos encontrados:");
            for (Producto product : products) {
                System.out.println(product);
            }
        }
    }
}