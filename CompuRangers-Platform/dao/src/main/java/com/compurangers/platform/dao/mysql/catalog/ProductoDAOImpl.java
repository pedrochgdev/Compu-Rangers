package com.compurangers.platform.dao.mysql.catalog;

import com.compurangers.platform.core.domain.catalog.Producto;
import java.sql.Connection;
import java.sql.ResultSet;
import com.compurangers.platform.dao.catalog.IProductoDAO;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductoDAOImpl extends BaseDAOImpl<Producto> implements IProductoDAO {
    
    @Override
    protected PreparedStatement addCommand(Connection conn, Producto modelo) throws SQLException {
        String sql = "INSERT INTO PRODUCTO (sku, nombre,descripcion, precio_venta, categoria_id, marca_id) VALUES (?, ?, ?, ?, ?, ?)";     
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, modelo.getSku());
        ps.setString(2, modelo.getNombre());
        ps.setString(3, modelo.getDescripcion());
        ps.setDouble(4, modelo.getPrecioVenta());
        ps.setInt(5, modelo.getCategoria().getId());
        ps.setInt(6, modelo.getMarca().getId());
        return ps;
    }

    @Override
    protected PreparedStatement updateCommand(Connection conn, Producto modelo) throws SQLException {
        String sql = "UPDATE PRODUCTO SET sku = ?, nombre = ?,descripcion = ?, precio_venta = ?, categoria_id = ?, marca_id = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, modelo.getSku());
        ps.setString(2, modelo.getNombre());
        ps.setString(3, modelo.getDescripcion());
        ps.setDouble(4, modelo.getPrecioVenta());
        ps.setInt(5, modelo.getCategoria().getId());
        ps.setInt(6, modelo.getMarca().getId());
        ps.setInt(7, modelo.getId());
        return ps;
    }

    @Override
    protected PreparedStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM PRODUCTO WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "SELECT p.*, c.id AS cid, m.id AS mid "
                        + "FROM PRODUCTO p "
                        + "JOIN CATEGORIA c ON p.categoria_id = c.id "
                        + "JOIN MARCA m ON p.marca_id = m.id "
                        + "WHERE p.id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;    
    }

    @Override
    protected PreparedStatement getAllCommand(Connection conn) throws SQLException {
        String sql =  "SELECT p.*, c.id AS cid, m.id AS mid "
                        + "FROM PRODUCTO p "
                        + "JOIN CATEGORIA c ON p.categoria_id = c.id "
                        + "JOIN MARCA m ON p.marca_id = m.id";
        PreparedStatement ps = conn.prepareStatement(sql);
       return ps;
    }

    @Override
    protected Producto mapModel(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getInt("id"));
        p.setSku(rs.getString("sku"));
        p.setNombre(rs.getString("nombre"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setPrecioVenta(rs.getDouble("precio_venta"));

        p.setCategoria(new CategoriaDAOImpl().search(rs.getInt("cid")));
        p.setMarca(new MarcaDAOImpl().search(rs.getInt("mid")));

        return p;   
    }
    
}
