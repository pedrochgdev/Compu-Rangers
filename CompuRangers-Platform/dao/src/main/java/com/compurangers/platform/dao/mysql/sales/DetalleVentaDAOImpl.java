package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.sales.DetalleVenta;
import com.compurangers.platform.dao.mysql.BaseDetalleDAOImpl;
import com.compurangers.platform.dao.mysql.catalog.ProductoDAOImpl;
import com.compurangers.platform.dao.sales.IDetalleVentaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DetalleVentaDAOImpl extends BaseDetalleDAOImpl<DetalleVenta> implements IDetalleVentaDAO{

    @Override
    protected PreparedStatement addCommand(Connection conn, DetalleVenta modelo) throws SQLException {
        String sql = "INSERT INTO DETALLE_VENTA (cantidad, subtotal, cantidad_devuelta, orden_de_venta_id, producto_id) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, modelo.getCantidad());
        ps.setDouble(2, modelo.getSubtotal());
        ps.setInt(3, modelo.getDevuelto());
        ps.setInt(4, modelo.getIdOrdenVenta());
        ps.setInt(5, modelo.getProducto().getId());
        return ps;
    }

    @Override
    protected PreparedStatement updateCommand(Connection conn, DetalleVenta modelo) throws SQLException {
        String sql = "UPDATE DETALLE_VENTA SET cantidad=0, subtotal=0, cantidad_devuelta=0, orden_de_venta_id=0, producto_id=0 WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, modelo.getCantidad());
        ps.setDouble(2, modelo.getSubtotal());
        ps.setInt(3, modelo.getDevuelto());
        ps.setInt(4, modelo.getIdOrdenVenta());
        ps.setInt(5, modelo.getProducto().getId());
        ps.setInt(6, modelo.getId());
        return ps;
    }

    @Override
    protected PreparedStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM DETALLE_VENTA WHERE id = ?";
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
    protected DetalleVenta mapModel(ResultSet rs) throws SQLException {
        DetalleVenta dv = new DetalleVenta();
        dv.setId(rs.getInt("id"));
        dv.setCantidad(rs.getInt("cantidad"));
        dv.setSubtotal(rs.getDouble("subtotal"));
        dv.setDevuelto(rs.getInt("cantidad_devuelta"));
        dv.setIdOrdenVenta(rs.getInt("orden_de_venta_id"));
        dv.setProducto(new ProductoDAOImpl().search(rs.getInt("producto_id")));
        return dv;
    }
    
    @Override
    protected PreparedStatement getAllFromFkCommand(Connection conn, int foreignKey) throws SQLException {
        String sql = "SELECT * FROM DETALLE_VENTA WHERE orden_de_venta_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, foreignKey);
        return ps;
    }

}
