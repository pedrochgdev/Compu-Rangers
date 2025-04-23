package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.sales.ItemCarrito;
import com.compurangers.platform.dao.mysql.BaseDetalleDAOImpl;
import com.compurangers.platform.dao.mysql.catalog.ProductoDAOImpl;
import com.compurangers.platform.dao.sales.IItemCarritoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemCarritoDAOImpl extends BaseDetalleDAOImpl<ItemCarrito> implements IItemCarritoDAO {

    @Override
    protected PreparedStatement addCommand(Connection conn, ItemCarrito modelo) throws SQLException {
        String sql = "INSERT INTO DETALLE_CARRITO (producto_id, cantidad, subtotal, carrito_id) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, modelo.getProducto().getId());
        ps.setInt(2, modelo.getCantidad());
        ps.setDouble(3, modelo.getSubtotal());
        ps.setInt(4, modelo.getCarritoId());
        return ps;
    }

    @Override
    protected PreparedStatement updateCommand(Connection conn, ItemCarrito modelo) throws SQLException {
        String sql = "UPDATE DETALLE_CARRITO SET producto_id = ?, cantidad = ?, subtotal = ?, carrito_id = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, modelo.getProducto().getId());
        ps.setInt(2, modelo.getCantidad());
        ps.setDouble(3, modelo.getSubtotal());
        ps.setInt(4, modelo.getCarritoId());
        ps.setInt(5, modelo.getId());
        return ps;
    }

    @Override
    protected PreparedStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM DETALLE_CARRITO WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM DETALLE_CARRITO WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);        
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement getAllCommand(Connection conn) throws SQLException {
        String sql = "SELECT * FROM DETALLE_CARRITO";
        return conn.prepareStatement(sql);
    }

    @Override
    protected ItemCarrito mapModel(ResultSet rs) throws SQLException {
        ItemCarrito item = new ItemCarrito();
        item.setId(rs.getInt("id"));
        item.setCantidad(rs.getInt("cantidad"));
        item.setSubtotal(rs.getDouble("subtotal"));
        item.setCarritoId(rs.getInt("carrito_id"));
        item.setProducto(new ProductoDAOImpl().search(rs.getInt("producto_id")));
        return item;
    }

    @Override
    protected PreparedStatement getAllFromFkCommand(Connection conn, int foreignKey) throws SQLException {
        String sql = "SELECT * FROM DETALLE_CARRITO WHERE carrito_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, foreignKey);
        return ps;
    }
    
}
