package com.compurangers.platform.appprueba;

import com.compurangers.platform.core.domain.catalog.Categoria;
import com.compurangers.platform.service.catalog.ProductoService;
import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.dao.mysql.catalog.CategoriaDAOImpl;
import com.compurangers.platform.dao.mysql.catalog.ProductoDAOImpl;
import com.compurangers.platform.service.catalog.CategoriaService;
import java.util.List;

public class AppPrueba {
    public static void main(String[] args) {
        ProductoService productService = new ProductoService(new ProductoDAOImpl());

        // Agregar producto
        Producto nuevo = new Producto();
        nuevo.setSku("SKU123");
        nuevo.setNombre("Producto de Prueba");
        nuevo.setDescripcion("Este es un producto de prueba");
        nuevo.setPrecioVenta(99.99);
        productService.addProducto(nuevo);

        // Mostrar todos
        List<Producto> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No se encontraron productos o hubo un error en la conexión.");
        } else {
            System.out.println("¡Conexión exitosa a la base de datos! Productos encontrados:");
            for (Producto product : products) {
                System.out.println(product);
            }
        }

        // Buscar por ID
        Producto buscado = productService.searchProducto(1);
        if (buscado != null) {
            System.out.println("Producto encontrado: " + buscado);
        } else {
            System.out.println("Producto no encontrado.");
        }

        // Actualizar
        if (buscado != null) {
            buscado.setNombre("Producto Actualizado");
            productService.updateProducto(buscado);
            System.out.println("Producto actualizado: " + productService.searchProducto(buscado.getId()));
        }

        // Eliminar
        if (buscado != null) {
            productService.deleteProducto(buscado.getId());
            System.out.println("Producto eliminado.");
        }
        CategoriaService categoriaService = new CategoriaService(new CategoriaDAOImpl());
        // CRUD Categorías
        System.out.println("\n--- Operaciones con Categorías ---");

        // Agregar categoría
        Categoria nueva = new Categoria();
        nueva.setNombre("Tecnología");
        nueva.setDescripcion("Categoría relacionada a tecnología.");
        categoriaService.addCategoria(nueva);

        // Obtener todas las categorías
        List<Categoria> categorias = categoriaService.getAllCategorias();
        if (categorias.isEmpty()) {
            System.out.println("No se encontraron categorías.");
        } else {
            System.out.println("Categorías encontradas:");
            for (Categoria c : categorias) {
                System.out.println(c);
            }
        }

        // Buscar una categoría por ID
        Categoria buscada = categoriaService.searchCategoria(1);
        if (buscada != null) {
            System.out.println("Categoría encontrada con ID 1: " + buscada);
        } else {
            System.out.println("No se encontró la categoría con ID 1.");
        }

        // Actualizar categoría
        if (buscada != null) {
            buscada.setDescripcion("Categoría de tecnología y dispositivos.");
            categoriaService.updateCategoria(buscada);
            System.out.println("Categoría actualizada: " + categoriaService.searchCategoria(buscada.getId()));
        }

//         Eliminar una categoría
        if (buscada != null) {
            boolean eliminada = categoriaService.deleteCategoria(buscada.getId());
            System.out.println("Categoría con ID " + buscada.getId() + (eliminada ? " eliminada." : " no pudo eliminarse."));
        }

        // Mostrar todas las categorías después de eliminar
        categorias = categoriaService.getAllCategorias();
        System.out.println("Categorías restantes:");
        for (Categoria c : categorias) {
            System.out.println(c);
        }
    
        
    }
}