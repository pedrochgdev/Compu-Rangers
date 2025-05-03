package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.sales.DetalleDevolucion;
import com.compurangers.platform.dao.mysql.BaseDetalleDAOImpl;
import com.compurangers.platform.dao.sales.IDetalleDevolucionDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class DetalleDevolucionDAOImpl extends BaseDetalleDAOImpl<DetalleDevolucion> implements IDetalleDevolucionDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, DetalleDevolucion modelo) throws SQLException {
        String sql = "{call add_detalle_devolucion(?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setInt(2, modelo.getCantidad());
        cs.setInt(3, modelo.getDetalleVentaId());
        cs.setInt(4, modelo.getOrdenDevolucionId());
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, DetalleDevolucion modelo) throws SQLException {
        String sql = "{call update_detalle_devolucion(?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, modelo.getId());
        cs.setInt(2, modelo.getCantidad());
        cs.setInt(3, modelo.getDetalleVentaId());
        cs.setInt(4, modelo.getOrdenDevolucionId());
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "{call delete_detalle_devolucion(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "{call search_detalle_devolucion(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{call get_all_detalle_devolucion()}");
    }

    @Override
    protected DetalleDevolucion mapModel(ResultSet rs) throws SQLException {
        DetalleDevolucion detalle = new DetalleDevolucion();
        detalle.setId(rs.getInt("id"));
        detalle.setCantidad(rs.getInt("cantidad"));
        detalle.setDetalleVentaId(rs.getInt("detalle_venta_id"));
        detalle.setOrdenDevolucionId(rs.getInt("orden_devolucion_id"));
        return detalle;
    }

    @Override
    protected CallableStatement getAllFromFkCommand(Connection conn, int foreignKey) throws SQLException {
        String sql = "{call get_all_detalles_devolucion_by_orden(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, foreignKey);
        return cs;
    }
}