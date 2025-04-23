package com.compurangers.platform.appprueba;

import com.compurangers.platform.core.domain.catalog.Categoria;
import com.compurangers.platform.core.domain.catalog.Marca;
import com.compurangers.platform.service.catalog.ProductoService;
import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.core.domain.sales.Carrito;
import com.compurangers.platform.core.domain.sales.ItemCarrito;
import com.compurangers.platform.dao.mysql.catalog.CategoriaDAOImpl;
import com.compurangers.platform.dao.mysql.catalog.MarcaDAOImpl;
import com.compurangers.platform.dao.mysql.catalog.ProductoDAOImpl;
import com.compurangers.platform.dao.mysql.sales.CarritoDAOImpl;
import com.compurangers.platform.dao.mysql.sales.ItemCarritoDAOImpl;
import com.compurangers.platform.service.catalog.CategoriaService;
import com.compurangers.platform.service.catalog.MarcaService;
import com.compurangers.platform.service.sales.CarritoService;
import com.compurangers.platform.service.sales.ItemCarritoService;
import java.util.List;

public class AppPrueba {
    public static void main(String[] args) {
        // Producto Tests
        ProductoService productService = new ProductoService(new ProductoDAOImpl());
        System.out.println("=== Probando Producto ===");
        try {
            List<Producto> products = productService.getAllProductos();
            if (products.isEmpty()) {
                System.out.println("No se encontraron productos o hubo un error en la conexión.");
            } else {
                System.out.println("Productos encontrados:");
                for (Producto product : products) {
                    System.out.println(product);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
        try {
            Producto newProduct = new Producto();
            newProduct.setNombre("Laptop Lenovo 16\"");
            newProduct.setDescripcion("Laptop 16GB");
            newProduct.setSku("LT16GB");
            newProduct.setPrecioVenta(999.99);
            Categoria categoria = new Categoria();
            categoria.setId(1);
            newProduct.setCategoria(categoria);
            Marca marca = new Marca();
            marca.setId(1);
            newProduct.setMarca(marca);
            int newId = productService.addProducto(newProduct);
            System.out.println("Producto añadido con ID: " + newId);
        } catch (Exception e) {
            System.out.println("Error al añadir producto: " + e.getMessage());
        }
        try {
            Producto updatedProduct = new Producto();
            updatedProduct.setId(1);
            updatedProduct.setNombre("Nombre actualizado");
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
        try {
            Producto foundProduct = productService.searchProducto(1);
            if (foundProduct != null) {
                System.out.println("Producto encontrado: " + foundProduct);
            } else {
                System.out.println("No se encontró el producto con ID 1.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }
        try {
            boolean deleted = productService.deleteProducto(1);
            System.out.println("Producto eliminado: " + (deleted ? "Éxito" : "Fallo"));
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }

        // Categoria Tests
        CategoriaService categoriaService = new CategoriaService(new CategoriaDAOImpl());
        System.out.println("\n=== Probando Categoria ===");
        try {
            List<Categoria> categorias = categoriaService.getAllCategorias();
            if (categorias.isEmpty()) {
                System.out.println("No se encontraron categorías o hubo un error en la conexión.");
            } else {
                System.out.println("Categorías encontradas:");
                for (Categoria categoria : categorias) {
                    System.out.println(categoria);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar categorías: " + e.getMessage());
        }
        try {
            Categoria newCategoria = new Categoria();
            newCategoria.setNombre("Hogar");
            int newId = categoriaService.addCategoria(newCategoria);
            System.out.println("Categoría añadida con ID: " + newId);
        } catch (Exception e) {
            System.out.println("Error al añadir categoría: " + e.getMessage());
        }
        try {
            Categoria updatedCategoria = new Categoria();
            updatedCategoria.setId(1);
            updatedCategoria.setNombre("Electrónica Actualizada");
            boolean updated = categoriaService.updateCategoria(updatedCategoria);
            System.out.println("Categoría actualizada: " + (updated ? "Éxito" : "Fallo"));
        } catch (Exception e) {
            System.out.println("Error al actualizar categoría: " + e.getMessage());
        }
        try {
            Categoria foundCategoria = categoriaService.searchCategoria(1);
            if (foundCategoria != null) {
                System.out.println("Categoría encontrada: " + foundCategoria);
            } else {
                System.out.println("No se encontró la categoría con ID 1.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar categoría: " + e.getMessage());
        }
        try {
            boolean deleted = categoriaService.deleteCategoria(1);
            System.out.println("Categoría eliminada: " + (deleted ? "Éxito" : "Fallo"));
        } catch (Exception e) {
            System.out.println("Error al eliminar categoría: " + e.getMessage());
        }

        // Marca Tests
        MarcaService marcaService = new MarcaService(new MarcaDAOImpl());
        System.out.println("\n=== Probando Marca ===");
        try {
            List<Marca> marcas = marcaService.getAllMarcas();
            if (marcas.isEmpty()) {
                System.out.println("No se encontraron marcas o hubo un error en la conexión.");
            } else {
                System.out.println("Marcas encontradas:");
                for (Marca marca : marcas) {
                    System.out.println(marca);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar marcas: " + e.getMessage());
        }
        try {
            Marca newMarca = new Marca();
            newMarca.setNombre("NewBrand");
            int newId = marcaService.addMarca(newMarca);
            System.out.println("Marca añadida con ID: " + newId);
        } catch (Exception e) {
            System.out.println("Error al añadir marca: " + e.getMessage());
        }
        try {
            Marca updatedMarca = new Marca();
            updatedMarca.setId(1);
            updatedMarca.setNombre("TechBrand Actualizada");
            boolean updated = marcaService.updateMarca(updatedMarca);
            System.out.println("Marca actualizada: " + (updated ? "Éxito" : "Fallo"));
        } catch (Exception e) {
            System.out.println("Error al actualizar marca: " + e.getMessage());
        }
        try {
            Marca foundMarca = marcaService.searchMarca(1);
            if (foundMarca != null) {
                System.out.println("Marca encontrada: " + foundMarca);
            } else {
                System.out.println("No se encontró la marca con ID 1.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar marca: " + e.getMessage());
        }
        try {
            boolean deleted = marcaService.deleteMarca(1);
            System.out.println("Marca eliminada: " + (deleted ? "Éxito" : "Fallo"));
        } catch (Exception e) {
            System.out.println("Error al eliminar marca: " + e.getMessage());
        }

        // Carrito Tests
        CarritoService carritoService = new CarritoService(new CarritoDAOImpl());
        System.out.println("\n=== Probando Carrito ===");
        try {
            List<Carrito> carritos = carritoService.getAllCarrito();
            if (carritos.isEmpty()) {
                System.out.println("No se encontraron carritos o hubo un error en la conexión.");
            } else {
                System.out.println("Carritos encontrados:");
                for (Carrito carrito : carritos) {
                    System.out.println(carrito);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar carritos: " + e.getMessage());
        }
        try {
            Carrito newCarrito = new Carrito();
            newCarrito.setUsuarioId(2);
            ItemCarrito item = new ItemCarrito();
            item.setCantidad(1);
            item.setSubtotal(999.99 * 100); // 999.99 in cents
            item.setCarritoId(0); // Will be set by DB
            Producto producto = new Producto();
            producto.setId(1);
            item.setProducto(producto);
            newCarrito.getItems().add(item);
            newCarrito.setTotal(999.99);
            newCarrito.setCantidadProductos(1);
            int newId = carritoService.addCarrito(newCarrito);
            System.out.println("Carrito añadido con ID: " + newId);
        } catch (Exception e) {
            System.out.println("Error al añadir carrito: " + e.getMessage());
        }
        try {
            Carrito updatedCarrito = new Carrito();
            updatedCarrito.setId(1);
            updatedCarrito.setUsuarioId(2);
            ItemCarrito item = new ItemCarrito();
            item.setCantidad(2);
            item.setSubtotal(599.99 * 2 * 100); // 1199.98 in cents
            item.setCarritoId(1);
            Producto producto = new Producto();
            producto.setId(1);
            item.setProducto(producto);
            updatedCarrito.getItems().add(item);
            updatedCarrito.setTotal(1199.98);
            updatedCarrito.setCantidadProductos(2);
            boolean updated = carritoService.updateCarrito(updatedCarrito);
            System.out.println("Carrito actualizado: " + (updated ? "Éxito" : "Fallo"));
        } catch (Exception e) {
            System.out.println("Error al actualizar carrito: " + e.getMessage());
        }
        try {
            Carrito foundCarrito = carritoService.searchCarrito(1);
            if (foundCarrito != null) {
                System.out.println("Carrito encontrado: " + foundCarrito);
            } else {
                System.out.println("No se encontró el carrito con ID 1.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar carrito: " + e.getMessage());
        }
        try {
            boolean deleted = carritoService.deleteCarrito(1);
            System.out.println("Carrito eliminado: " + (deleted ? "Éxito" : "Fallo"));
        } catch (Exception e) {
            System.out.println("Error al eliminar carrito: " + e.getMessage());
        }

        // ItemCarrito Tests
        ItemCarritoService itemCarritoService = new ItemCarritoService(new ItemCarritoDAOImpl());
        System.out.println("\n=== Probando ItemCarrito ===");
        try {
            List<ItemCarrito> items = itemCarritoService.getAllItemCarrito();
            if (items.isEmpty()) {
                System.out.println("No se encontraron ítems de carrito o hubo un error en la conexión.");
            } else {
                System.out.println("Ítems de carrito encontrados:");
                for (ItemCarrito item : items) {
                    System.out.println(item);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar ítems de carrito: " + e.getMessage());
        }
        try {
            ItemCarrito newItem = new ItemCarrito();
            newItem.setCantidad(1);
            newItem.setSubtotal(999.99 * 100); // 999.99 in cents
            newItem.setCarritoId(1);
            Producto producto = new Producto();
            producto.setId(1);
            newItem.setProducto(producto);
            int newId = itemCarritoService.addItemCarrito(newItem);
            System.out.println("Ítem de carrito añadido con ID: " + newId);
        } catch (Exception e) {
            System.out.println("Error al añadir ítem de carrito: " + e.getMessage());
        }
        try {
            ItemCarrito updatedItem = new ItemCarrito();
            updatedItem.setId(8);
            updatedItem.setCantidad(2);
            updatedItem.setSubtotal(599.99 * 2 * 100); // 1199.98 in cents
            updatedItem.setCarritoId(1);
            Producto producto = new Producto();
            producto.setId(1);
            updatedItem.setProducto(producto);
            boolean updated = itemCarritoService.updateItemCarrito(updatedItem);
            System.out.println("Ítem de carrito actualizado: " + (updated ? "Éxito" : "Fallo"));
        } catch (Exception e) {
            System.out.println("Error al actualizar ítem de carrito: " + e.getMessage());
        }
        try {
            ItemCarrito foundItem = itemCarritoService.searchItemCarrito(8);
            if (foundItem != null) {
                System.out.println("Ítem de carrito encontrado: " + foundItem);
            } else {
                System.out.println("No se encontró el ítem de carrito con ID 1.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar ítem de carrito: " + e.getMessage());
        }
        try {
            boolean deleted = itemCarritoService.deleteItemCarrito(2);
            System.out.println("Ítem de carrito eliminado: " + (deleted ? "Éxito" : "Fallo"));
        } catch (Exception e) {
            System.out.println("Error al eliminar ítem de carrito: " + e.getMessage());
        }
    }
}