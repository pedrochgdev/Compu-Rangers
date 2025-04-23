package com.compurangers.platform.dao.mysql.catalog;

import com.compurangers.platform.core.domain.catalog.Categoria;
import com.compurangers.platform.core.domain.catalog.Marca;
import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.compurangers.platform.dao.catalog.IProductoDAO;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ProductoDAOImpl implements IProductoDAO {

    @Override
    public int add(Producto modelo) {
        String sql = "INSERT INTO PRODUCTO (sku, descripcion, precio_venta, categoria_id, marca_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, modelo.getSku());
            ps.setString(2, modelo.getDescripcion());
            ps.setDouble(3, modelo.getPrecioVenta());
            ps.setInt(4, modelo.getCategoria().getId());
            ps.setInt(5, modelo.getMarca().getId());
            if (ps.executeUpdate() == 0) {
                System.err.println("El producto no se insertó");
                return 0;
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : -1;
            }
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo insertar el producto.");
        }
    }

    @Override
    public boolean update(Producto modelo) {
        String sql = "UPDATE PRODUCTO SET sku = ?, descripcion = ?, precio_venta = ?, categoria_id = ?, marca_id = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, modelo.getSku());
            cs.setString(2, modelo.getDescripcion());
            cs.setDouble(3, modelo.getPrecioVenta());
            cs.setInt(4, modelo.getCategoria().getId());
            cs.setInt(5, modelo.getMarca().getId());
            cs.setInt(6, modelo.getId());
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo actualizar el producto.");
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM PRODUCTO WHERE id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo eliminar el producto.");
        }
    }

    @Override
    public Producto search(int id) {
        String sql = "SELECT p.*, c.nombre AS categoria_nombre, m.nombre AS marca_nombre "
                + "FROM PRODUCTO p "
                + "JOIN CATEGORIA c ON p.categoria_id = c.id "
                + "JOIN MARCA m ON p.marca_id = m.id "
                + "WHERE p.id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    Producto p = new Producto();
                    p.setId(rs.getInt("id"));
                    p.setSku(rs.getString("sku"));
                    p.setDescripcion(rs.getString("descripcion"));
                    p.setPrecioVenta(rs.getDouble("precio_venta"));

                    Categoria categoria = new Categoria();
                    categoria.setId(rs.getInt("categoria_id"));
                    categoria.setNombre(rs.getString("categoria_nombre"));
                    p.setCategoria(categoria);

                    Marca marca = new Marca();
                    marca.setId(rs.getInt("marca_id"));
                    marca.setNombre(rs.getString("marca_nombre"));
                    p.setMarca(marca);

                    return p;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo buscar el producto.");
        }
        return null;
    }

    @Override
    public List<Producto> getAll() {
        List<Producto> productos = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(
                "SELECT p.*, c.nombre AS categoria_nombre, m.nombre AS marca_nombre "
                + "FROM PRODUCTO p "
                + "JOIN CATEGORIA c ON p.categoria_id = c.id "
                + "JOIN MARCA m ON p.marca_id = m.id")) {
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setSku(rs.getString("sku"));
                producto.setPrecioVenta(rs.getDouble("precio_venta"));

                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("categoria_id"));
                categoria.setNombre(rs.getString("categoria_nombre"));

                producto.setCategoria(categoria);

                Marca marca = new Marca();
                marca.setId(rs.getInt("marca_id"));
                marca.setNombre(rs.getString("marca_nombre"));

                producto.setMarca(marca);

                productos.add(producto);
            }
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo listar los productos.");
        }
        return productos;
    }
}
