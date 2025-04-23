package com.compurangers.platform.dao.mysql.inventory;

import com.compurangers.platform.core.domain.inventory.DetalleLote;
import com.compurangers.platform.dao.inventory.IDetalleLoteDAO;
import com.compurangers.platform.dao.mysql.BaseDetalleDAOImpl;
import com.compurangers.platform.dao.mysql.catalog.ProductoDAOImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DetalleLoteDAOImpl extends BaseDetalleDAOImpl<DetalleLote> implements IDetalleLoteDAO{

    @Override
    protected PreparedStatement addCommand(Connection conn, DetalleLote modelo) throws SQLException {
        String sql = "INSERT INTO DETALLE_LOTE (cantidad, precio_compra, lote_id, producto_id) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, modelo.getCantidad());
        ps.setDouble(2, modelo.getPrecioCompra());
        ps.setInt(3, modelo.getLoteId());
        ps.setInt(4, modelo.getProducto().getId());
        return ps;
    }

    @Override
    protected PreparedStatement updateCommand(Connection conn, DetalleLote modelo) throws SQLException {
        String sql = "UPDATE DETALLE_LOTE SET cantidad = ?, precio_compra = ?, lote_id = ?, producto_id = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, modelo.getCantidad());
        ps.setDouble(2, modelo.getPrecioCompra());
        ps.setInt(3, modelo.getLoteId());
        ps.setInt(4, modelo.getProducto().getId());
        ps.setInt(5, modelo.getId());
        return ps;
    }

    @Override
    protected PreparedStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM DETALLE_LOTE WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM DETALLE_LOTE WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement getAllCommand(Connection conn) throws SQLException {
        String sql = "SELECT * FROM DETALLE_LOTE";
        PreparedStatement ps = conn.prepareStatement(sql);
        return ps;
    }

    @Override
    protected DetalleLote mapModel(ResultSet rs) throws SQLException {
        DetalleLote dt = new DetalleLote();
        dt.setId(rs.getInt("id"));
        dt.setCantidad(rs.getInt("cantidad"));
        dt.setPrecioCompra(rs.getDouble("precio_compra"));
        dt.setLoteId(rs.getInt("lote_id"));
        dt.setProducto(new ProductoDAOImpl().search(rs.getInt("producto_id")));
        return dt;
    }
    
    @Override
    protected PreparedStatement getAllFromFkCommand(Connection conn, int foreignKey) throws SQLException {
        String sql = "SELECT * FROM DETALLE_LOTE WHERE lote_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, foreignKey);
        return ps;
    }

}
