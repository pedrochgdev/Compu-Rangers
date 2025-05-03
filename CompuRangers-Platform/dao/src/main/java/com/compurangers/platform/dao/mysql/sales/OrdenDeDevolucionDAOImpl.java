package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.sales.OrdenDeDevolucion;
import com.compurangers.platform.dao.mysql.BaseDetalleDAOImpl;
import com.compurangers.platform.dao.sales.IOrdenDeDevolucionDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class OrdenDeDevolucionDAOImpl extends BaseDetalleDAOImpl<OrdenDeDevolucion> implements IOrdenDeDevolucionDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, OrdenDeDevolucion modelo) throws SQLException {
        String sql = "{call add_orden_devolucion(?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setString(2, modelo.getMotivo());
        cs.setDate(3, new Date(modelo.getFechaRegistro().getTime()));
        cs.setString(4, modelo.getTipoDevolucion());
        cs.setInt(5, modelo.getDocumentoDeVentasNumero());
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, OrdenDeDevolucion modelo) throws SQLException {
        String sql = "{call update_orden_devolucion(?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, modelo.getId());
        cs.setString(2, modelo.getMotivo());
        cs.setDate(3, new Date(modelo.getFechaRegistro().getTime()));
        cs.setString(4, modelo.getTipoDevolucion());
        cs.setInt(5, modelo.getDocumentoDeVentasNumero());
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "{call delete_orden_devolucion(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "{call search_orden_devolucion(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{call get_all_orden_devolucion()}");
    }

    @Override
    protected OrdenDeDevolucion mapModel(ResultSet rs) throws SQLException {
        OrdenDeDevolucion devolucion = new OrdenDeDevolucion();
        devolucion.setId(rs.getInt("id"));
        devolucion.setMotivo(rs.getString("motivo"));
        devolucion.setFechaRegistro(rs.getDate("fecha_registro"));
        devolucion.setTipoDevolucion(rs.getString("tipo_devolucion"));
        devolucion.setDocumentoDeVentasNumero(rs.getInt("documento_de_ventas_numero"));
        return devolucion;
    }

    @Override
    protected CallableStatement getAllFromFkCommand(Connection conn, int foreignKey) throws SQLException {
        String sql = "{call get_all_ordenes_devolucion_from_doc_venta(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, foreignKey);
        return cs;
    }
    
}