package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.sales.DetalleVenta;
import com.compurangers.platform.dao.mysql.BaseDetalleDAOImpl;
import com.compurangers.platform.dao.mysql.catalog.ProductoDAOImpl;
import com.compurangers.platform.dao.sales.IDetalleVentaDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class DetalleVentaDAOImpl extends BaseDetalleDAOImpl<DetalleVenta> implements IDetalleVentaDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, DetalleVenta modelo) throws SQLException {
        String sql = "{call add_detalle_venta(?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setInt(2, modelo.getCantidad());
        cs.setDouble(3, modelo.getSubtotal());
        cs.setInt(4, modelo.getDevuelto());
        cs.setInt(5, modelo.getIdOrdenVenta());
        cs.setInt(6, modelo.getProducto().getId());
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, DetalleVenta modelo) throws SQLException {
        String sql = "{call update_detalle_venta(?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, modelo.getId());
        cs.setInt(2, modelo.getCantidad());
        cs.setDouble(3, modelo.getSubtotal());
        cs.setInt(4, modelo.getDevuelto());
        cs.setInt(5, modelo.getIdOrdenVenta());
        cs.setInt(6, modelo.getProducto().getId());
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "{call delete_detalle_venta(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "{call search_detalle_venta(?)}";
        CallableStatement cs = conn.prepareCall(sql);        
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{call get_all_detalle_venta()}");
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
    protected CallableStatement getAllFromFkCommand(Connection conn, int foreignKey) throws SQLException {
        String sql = "{call get_all_detalles_from_orden_venta(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, foreignKey);
        return cs;
    }
    
}