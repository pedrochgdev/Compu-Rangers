package com.compurangers.platform.dao.mysql.catalog;

import com.compurangers.platform.core.domain.catalog.Categoria;
import com.compurangers.platform.core.domain.catalog.Marca;
import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.compurangers.platform.dao.catalog.IProductoDAO;

public class ProductoDAOImpl implements IProductoDAO {

    @Override
    public int add(Producto modelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Producto modelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Producto search(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Producto> getAll() {
        List<Producto> productos = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                 "SELECT p.*, c.nombre AS categoria_nombre, m.nmbre AS marca_nombre " +
                 "FROM PRODUCTO p " +
                 "JOIN CATEGORIA c ON p.categoria_id = c.id " +
                 "JOIN MARCA m ON p.marca_id = m.id")) {
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setSku(rs.getString("sku"));
                producto.setPrecioVenta(rs.getDouble("precio_venta"));

                // Populate Categoria object
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("categoria_id"));
                categoria.setNombre(rs.getString("categoria_nombre"));
                // descripcion and categoriaPadre are not in DB, so left as null
                producto.setCategoria(categoria);

                // Populate Marca object
                Marca marca = new Marca();
                marca.setId(rs.getInt("marca_id"));
                marca.setNombre(rs.getString("marca_nombre"));
                // descripcion is not in DB, so left as null
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