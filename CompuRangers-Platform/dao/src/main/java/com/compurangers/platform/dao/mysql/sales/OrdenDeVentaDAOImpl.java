package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import com.compurangers.platform.dao.sales.IOrdenDeVentaDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class OrdenDeVentaDAOImpl extends BaseDAOImpl<OrdenDeVenta> implements IOrdenDeVentaDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, OrdenDeVenta modelo) throws SQLException {
        String sql = "{call add_orden_de_venta(?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setString(2, modelo.getEstado());
        cs.setDate(3, new Date(modelo.getFecha().getTime()));
        cs.setDouble(4, modelo.getTotal());
        cs.setInt(5, modelo.getClienteId());
        cs.setString(6, modelo.getDireccion());
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, OrdenDeVenta modelo) throws SQLException {
        String sql = "{call update_orden_de_venta(?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, modelo.getId());
        cs.setString(2, modelo.getEstado());
        cs.setDate(3, new Date(modelo.getFecha().getTime()));
        cs.setDouble(4, modelo.getTotal());
        cs.setInt(5, modelo.getClienteId());
        cs.setString(6, modelo.getDireccion());
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "{call delete_orden_de_venta(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "{call search_orden_de_venta(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{call get_all_orden_de_venta()}");
    }

    @Override
    protected OrdenDeVenta mapModel(ResultSet rs) throws SQLException {
        OrdenDeVenta ov = new OrdenDeVenta();
        ov.setId(rs.getInt("id"));
        ov.setTotal(rs.getDouble("total"));
        ov.setClienteId(rs.getInt("cliente_usuario_id"));
        ov.setFecha(rs.getDate("fecha"));
        ov.setEstado(rs.getString("estado"));
        ov.setDireccion(rs.getString("direccion"));
        ov.setDetalles(new DetalleVentaDAOImpl().getAllByForeignKey(ov.getId()));
        return ov;
    }
    
}