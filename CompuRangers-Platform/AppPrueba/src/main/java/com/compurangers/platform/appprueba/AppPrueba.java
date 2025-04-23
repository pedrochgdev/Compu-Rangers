package com.compurangers.platform.appprueba;

import com.compurangers.platform.core.domain.catalog.Categoria;
import com.compurangers.platform.core.domain.catalog.Marca;
import com.compurangers.platform.service.catalog.ProductoService;
import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.dao.mysql.catalog.ProductoDAOImpl;
import java.util.List;

public class AppPrueba {
    public static void main(String[] args) {
        ProductoService productService = new ProductoService(new ProductoDAOImpl());

        // Test getAll
        System.out.println("=== Probando getAll ===");
        try {
            List<Producto> products = productService.getAllProductos();
            if (products.isEmpty()) {
                System.out.println("No se encontraron productos o hubo un error en la conexión.");
            } else {
                System.out.println("¡Conexión exitosa a la base de datos! Productos encontrados:");
                for (Producto product : products) {
                    System.out.println(product);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }

        // Test add
        System.out.println("\n=== Probando add ===");
        try {
            Producto newProduct = new Producto();
            newProduct.setDescripcion("Laptop 16GB");
            newProduct.setSku("LT16GB");
            newProduct.setPrecioVenta(999.99);
            Categoria categoria = new Categoria();
            categoria.setId(1); // Assumes categoria_id=1 exists
            newProduct.setCategoria(categoria);
            Marca marca = new Marca();
            marca.setId(1); // Assumes marca_id=1 exists
            newProduct.setMarca(marca);
            int newId = productService.addProducto(newProduct);
            System.out.println("Producto añadido con ID: " + newId);
        } catch (Exception e) {
            System.out.println("Error al añadir producto: " + e.getMessage());
        }

        // Test update
        System.out.println("\n=== Probando update ===");
        try {
            Producto updatedProduct = new Producto();
            updatedProduct.setId(1); // Assumes product with id=1 exists
            updatedProduct.setDescripcion("Smartphone 256GB");
            updatedProduct.setSku("SM256GB");
            updatedProduct.setPrecioVenta(699.99);
            Categoria categoria = new Categoria();
            categoria.setId(1);
            updatedProduct.setCategoria(categoria);
            Marca marca = new Marca();
            marca.setId(1);
            updatedProduct.setMarca(marca);
            boolean updated = productService.updateProducto(updatedProduct);
            System.out.println("Producto actualizado: " + (updated ? "Éxito" : "Fallo"));
        } catch (Exception e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }

        // Test search
        System.out.println("\n=== Probando search ===");
        try {
            Producto foundProduct = productService.searchProducto(1); // Search for id=1
            if (foundProduct != null) {
                System.out.println("Producto encontrado: " + foundProduct);
            } else {
                System.out.println("No se encontró el producto con ID 1.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }

        // Test delete
        System.out.println("\n=== Probando delete ===");
        try {
            boolean deleted = productService.deleteProducto(1); // Delete id=1
            System.out.println("Producto eliminado: " + (deleted ? "Éxito" : "Fallo"));
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }
}