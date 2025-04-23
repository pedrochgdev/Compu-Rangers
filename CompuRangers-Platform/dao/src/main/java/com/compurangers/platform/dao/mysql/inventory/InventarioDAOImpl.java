package com.compurangers.platform.dao.mysql.inventory;

import com.compurangers.platform.core.domain.inventory.Inventario;
import com.compurangers.platform.dao.inventory.IInventarioDAO;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import com.compurangers.platform.dao.mysql.catalog.ProductoDAOImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InventarioDAOImpl extends BaseDAOImpl<Inventario> implements IInventarioDAO{

    @Override
    protected PreparedStatement addCommand(Connection conn, Inventario modelo) throws SQLException {
        String sql = "INSERT INTO INVENTARIO (cantidad_disponible, lote_id, producto_id) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, modelo.getCantidadDisponible());
        ps.setInt(2, modelo.getLoteId());
        ps.setInt(3, modelo.getProducto().getId());
        return ps;
    }

    @Override
    protected PreparedStatement updateCommand(Connection conn, Inventario modelo) throws SQLException {
        String sql = "UPDATE INVENTARIO SET cantidad_disponible=?, lote_id=?, producto_id=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, modelo.getCantidadDisponible());
        ps.setInt(2, modelo.getLoteId());
        ps.setInt(3, modelo.getProducto().getId());
        ps.setInt(4, modelo.getId());
        return ps;
    }

    @Override
    protected PreparedStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM INVENTARIO WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM INVENTARIO WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement getAllCommand(Connection conn) throws SQLException {
        String sql = "SELECT * FROM INVENTARIO";
        PreparedStatement ps = conn.prepareStatement(sql);
        return ps;
    }

    @Override
    protected Inventario mapModel(ResultSet rs) throws SQLException {
        Inventario inv = new Inventario();
        inv.setId(rs.getInt("id"));
        inv.setCantidadDisponible(rs.getInt("cantidad_disponible"));
        inv.setLoteId(rs.getInt("lote_id"));
        inv.setProducto(new ProductoDAOImpl().search(rs.getInt("producto_id")));
        return inv;
    }

}
