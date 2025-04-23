package com.compurangers.platform.appprueba;

import com.compurangers.platform.core.domain.catalog.Categoria;
import com.compurangers.platform.core.domain.catalog.Marca;
import com.compurangers.platform.service.catalog.ProductoService;
import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.core.domain.inventory.Proveedor;
import com.compurangers.platform.core.domain.sales.DetalleVenta;
import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import com.compurangers.platform.dao.mysql.catalog.CategoriaDAOImpl;
import com.compurangers.platform.dao.mysql.catalog.MarcaDAOImpl;
import com.compurangers.platform.dao.mysql.catalog.ProductoDAOImpl;
import com.compurangers.platform.dao.mysql.inventory.ProveedorDAOImpl;
import com.compurangers.platform.dao.mysql.sales.OrdenDeVentaDAOImpl;
import com.compurangers.platform.service.catalog.CategoriaService;
import com.compurangers.platform.service.catalog.MarcaService;
import com.compurangers.platform.service.inventory.ProveedorService;
import com.compurangers.platform.service.sales.OrdenDeVentaService;
import java.util.ArrayList;
import java.util.Date;
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

        // OrdenDeVenta Tests
        OrdenDeVentaService ordenService = new OrdenDeVentaService(new OrdenDeVentaDAOImpl());
        System.out.println("\n=== Probando OrdenDeVenta ===");
        try {
            List<OrdenDeVenta> ordenes = ordenService.getAllOrdenDeVenta();
            if (ordenes.isEmpty()) {
                System.out.println("No se encontraron órdenes de venta o hubo un error en la conexión.");
            } else {
                System.out.println("Órdenes de venta encontradas:");
                for (OrdenDeVenta orden : ordenes) {
                    System.out.println(orden);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar órdenes de venta: " + e.getMessage());
        }
        try {
            OrdenDeVenta newOrden = new OrdenDeVenta();
            newOrden.setFecha(new Date());
            newOrden.setEstado('A');
            newOrden.setTotal(999.99);
            List<DetalleVenta> detalles = new ArrayList<>();
            DetalleVenta detalle = new DetalleVenta();
            detalle.setCantidad(1);
            detalle.setPrecio(999.99);
            detalle.setProducto(new Producto());
            detalle.getProducto().setId(1);
            detalles.add(detalle);
            newOrden.setDetalles(detalles);
            int newId = ordenService.addOrdenDeVenta(newOrden);
            System.out.println("Orden de venta añadida con ID: " + newId);
        } catch (Exception e) {
            System.out.println("Error al añadir orden de venta: " + e.getMessage());
        }
        try {
            OrdenDeVenta updatedOrden = new OrdenDeVenta();
            updatedOrden.setId(1);
            updatedOrden.setFecha(new Date());
            updatedOrden.setEstado('C');
            updatedOrden.setTotal(1199.99);
            List<DetalleVenta> detalles = new ArrayList<>();
            DetalleVenta detalle = new DetalleVenta();
            detalle.setCantidad(2);
            detalle.setPrecio(599.99);
            detalle.setProducto(new Producto());
            detalle.getProducto().setId(1);
            detalles.add(detalle);
            updatedOrden.setDetalles(detalles);
            boolean updated = ordenService.updateOrdenDeVenta(updatedOrden);
            System.out.println("Orden de venta actualizada: " + (updated ? "Éxito" : "Fallo"));
        } catch (Exception e) {
            System.out.println("Error al actualizar orden de venta: " + e.getMessage());
        }
        try {
            OrdenDeVenta foundOrden = ordenService.searchOrdenDeVenta(1);
            if (foundOrden != null) {
                System.out.println("Orden de venta encontrada: " + foundOrden);
            } else {
                System.out.println("No se encontró la orden de venta con ID 1.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar orden de venta: " + e.getMessage());
        }
        try {
            boolean deleted = ordenService.deleteOrdenDeVenta(1);
            System.out.println("Orden de venta eliminada: " + (deleted ? "Éxito" : "Fallo"));
        } catch (Exception e) {
            System.out.println("Error al eliminar orden de venta: " + e.getMessage());
        }

        // Proveedor Tests
        ProveedorService proveedorService = new ProveedorService(new ProveedorDAOImpl());
        System.out.println("\n=== Probando Proveedor ===");
        try {
            List<Proveedor> proveedores = proveedorService.getAllProveedores();
            if (proveedores.isEmpty()) {
                System.out.println("No se encontraron proveedores o hubo un error en la conexión.");
            } else {
                System.out.println("Proveedores encontrados:");
                for (Proveedor proveedor : proveedores) {
                    System.out.println(proveedor);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar proveedores: " + e.getMessage());
        }
        try {
            Proveedor newProveedor = new Proveedor();
            newProveedor.setRazonSocial("NewSupplier SAC");
            int newId = proveedorService.addProveedor(newProveedor);
            System.out.println("Proveedor añadido con ID: " + newId);
        } catch (Exception e) {
            System.out.println("Error al añadir proveedor: " + e.getMessage());
        }
        try {
            Proveedor updatedProveedor = new Proveedor();
            updatedProveedor.setId(1);
            updatedProveedor.setRazonSocial("TechSupplier Updated SAC");
            boolean updated = proveedorService.updateProveedor(updatedProveedor);
            System.out.println("Proveedor actualizado: " + (updated ? "Éxito" : "Fallo"));
        } catch (Exception e) {
            System.out.println("Error al actualizar proveedor: " + e.getMessage());
        }
        try {
            Proveedor foundProveedor = proveedorService.searchProveedor(1);
            if (foundProveedor != null) {
                System.out.println("Proveedor encontrado: " + foundProveedor);
            } else {
                System.out.println("No se encontró el proveedor con ID 1.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar proveedor: " + e.getMessage());
        }
        try {
            boolean deleted = proveedorService.deleteProveedor(1);
            System.out.println("Proveedor eliminado: " + (deleted ? "Éxito" : "Fallo"));
        } catch (Exception e) {
            System.out.println("Error al eliminar proveedor: " + e.getMessage());
        }
    }
}