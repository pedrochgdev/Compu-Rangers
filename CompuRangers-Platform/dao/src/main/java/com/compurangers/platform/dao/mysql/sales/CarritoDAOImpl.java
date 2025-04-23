package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.catalog.Categoria;
import com.compurangers.platform.core.domain.catalog.Marca;
import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.core.domain.sales.Carrito;
import com.compurangers.platform.core.domain.sales.ItemCarrito;
import com.compurangers.platform.dao.sales.ICarritoDAO;
import com.compurangers.platform.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoDAOImpl implements ICarritoDAO {

    @Override
    public int add(Carrito modelo) {
        String sql = "INSERT INTO CARRITO (total, cantidad_productos, cliente_usuario_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDouble(1, modelo.getTotal());
            ps.setInt(2, modelo.getCantidadProductos());
            ps.setInt(3, modelo.getUsuarioId());

            if (ps.executeUpdate() == 0) {
                System.err.println("El carrito no se insertó");
                return 0;
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : -1;
            }
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo insertar el carrito.");
        }
    }

    @Override
    public boolean update(Carrito modelo) {
        String sql = "UPDATE CARRITO SET total = ?, cantidad_productos = ?, cliente_usuario_id = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setDouble(1, modelo.getTotal());
            cs.setInt(2, modelo.getCantidadProductos());
            cs.setInt(3, modelo.getUsuarioId());
            cs.setInt(4, modelo.getId());
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo actualizar el carrito.");
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM CARRITO WHERE id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo eliminar el carrito.");
        }
    }

    @Override
    public Carrito search(int id) {
        String sql = "SELECT * FROM CARRITO WHERE id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Carrito carrito = new Carrito();
                    carrito.setId(rs.getInt("id"));
                    carrito.setTotal(rs.getDouble("total"));
                    carrito.setCantidadProductos(rs.getInt("cantidad_productos"));
                    carrito.setUsuarioId(rs.getInt("cliente_usuario_id"));

                    String itemSql = "SELECT dc.*, p.descripcion AS producto_descripcion, p.precio_venta as producto_precio_venta, p.sku as producto_sku,"
                            + "c.id AS categoria_id, c.nombre AS categoria_nombre, "
                            + "m.id AS marca_id, m.nombre AS marca_nombre "
                            + "FROM DETALLE_CARRITO dc "
                            + "JOIN PRODUCTO p ON dc.producto_id = p.id "
                            + "JOIN CATEGORIA c ON p.categoria_id = c.id "
                            + "JOIN MARCA m ON p.marca_id = m.id"
                            + "WHERE dc.carrito_id = ?";
                    try (PreparedStatement psItems = conn.prepareStatement(itemSql)) {
                        psItems.setInt(1, carrito.getId());
                        try (ResultSet rsItems = psItems.executeQuery()) {
                            while (rsItems.next()) {
                                ItemCarrito item = new ItemCarrito();
                                Producto producto = new Producto();
                                producto.setId(rsItems.getInt("producto_id"));
                                producto.setDescripcion(rsItems.getString("producto_descripcion"));
                                producto.setPrecioVenta(rsItems.getDouble("producto_precio_venta"));
                                producto.setSku(rsItems.getString("producto_sku"));
                                Categoria categoria = new Categoria();
                                categoria.setId(rs.getInt("categoria_id"));
                                categoria.setNombre(rs.getString("categoria_nombre"));

                                producto.setCategoria(categoria);

                                Marca marca = new Marca();
                                marca.setId(rs.getInt("marca_id"));
                                marca.setNombre(rs.getString("marca_nombre"));

                                producto.setMarca(marca);
                                item.setId(rsItems.getInt("id"));
                                item.setProducto(producto);
                                item.setCantidad(rsItems.getInt("cantidad"));
                                item.setCarritoId(rsItems.getInt("carrito_id"));
                                item.setSubtotal(rsItems.getDouble("subtotal"));

                                carrito.getItems().add(item);
                            }
                        }
                    }

                    return carrito;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo buscar el carrito.");
        }
        return null;
    }

    @Override
    public List<Carrito> getAll() {
        List<Carrito> carritos = new ArrayList<>();
        String sql = "SELECT * FROM CARRITO";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Carrito carrito = new Carrito();
                carrito.setId(rs.getInt("id"));
                carrito.setTotal(rs.getDouble("total"));
                carrito.setCantidadProductos(rs.getInt("cantidad_productos"));
                carrito.setUsuarioId(rs.getInt("cliente_usuario_id"));

                // Obtener los items del carrito desde la tabla DETALLE_CARRITO
                String itemSql = "SELECT dc.*, p.descripcion AS producto_descripcion, p.precio_venta as producto_precio_venta, p.sku as producto_sku, "
                        + "c.id AS categoria_id, c.nombre AS categoria_nombre, "
                        + "m.id AS marca_id, m.nombre AS marca_nombre "
                        + "FROM DETALLE_CARRITO dc "
                        + "JOIN PRODUCTO p ON dc.producto_id = p.id "
                        + "JOIN CATEGORIA c ON p.categoria_id = c.id "
                        + "JOIN MARCA m ON p.marca_id = m.id "
                        + "WHERE dc.carrito_id = ?";
                try (PreparedStatement psItems = conn.prepareStatement(itemSql)) {
                    psItems.setInt(1, carrito.getId());
                    try (ResultSet rsItems = psItems.executeQuery()) {
                        while (rsItems.next()) {
                            ItemCarrito item = new ItemCarrito();
                            Producto producto = new Producto();
                            producto.setId(rsItems.getInt("producto_id"));
                            producto.setDescripcion(rsItems.getString("producto_descripcion"));
                            producto.setPrecioVenta(rsItems.getDouble("producto_precio_venta"));
                            producto.setSku(rsItems.getString("producto_sku"));

                            // Set categoria
                            Categoria categoria = new Categoria();
                            categoria.setId(rsItems.getInt("categoria_id"));
                            categoria.setNombre(rsItems.getString("categoria_nombre"));
                            producto.setCategoria(categoria);

                            // Set marca
                            Marca marca = new Marca();
                            marca.setId(rsItems.getInt("marca_id"));
                            marca.setNombre(rsItems.getString("marca_nombre"));
                            producto.setMarca(marca);

                            item.setProducto(producto);
                            item.setCantidad(rsItems.getInt("cantidad"));
                            item.setCarritoId(rsItems.getInt("carrito_id"));
                            item.setSubtotal(rsItems.getDouble("subtotal"));

                            carrito.getItems().add(item);
                        }
                    }
                }

                carritos.add(carrito);
            }
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo listar los carritos.");
        }
        return carritos;
    }

}
