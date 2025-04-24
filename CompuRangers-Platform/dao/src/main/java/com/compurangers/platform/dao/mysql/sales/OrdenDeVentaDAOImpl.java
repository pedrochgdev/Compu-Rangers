package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.sales.OrdenDeVenta;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import com.compurangers.platform.dao.sales.IOrdenDeVentaDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrdenDeVentaDAOImpl extends BaseDAOImpl<OrdenDeVenta> implements IOrdenDeVentaDAO{

    @Override
    protected PreparedStatement addCommand(Connection conn, OrdenDeVenta modelo) throws SQLException {
        String sql = "INSERT INTO ORDEN_DE_VENTA (estado, fecha, total, cliente_usuario_id, direccion) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, modelo.getEstado());
        ps.setDate(2, new Date(modelo.getFecha().getTime()));
        ps.setDouble(3, modelo.getTotal());
        ps.setInt(4, modelo.getClienteId());
        ps.setString(5, modelo.getDireccion());
        return ps;
    }

    @Override
    protected PreparedStatement updateCommand(Connection conn, OrdenDeVenta modelo) throws SQLException {
        String sql = "UPDATE ORDEN_DE_VENTA SET estado=0, fecha=0, total=0, cliente_usuario_id=0, direccion=0 WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, modelo.getEstado());
        ps.setDate(2, new Date(modelo.getFecha().getTime()));
        ps.setDouble(3, modelo.getTotal());
        ps.setInt(4, modelo.getClienteId());
        ps.setString(5, modelo.getDireccion());
        ps.setInt(6, modelo.getId());
        return ps;
    }

    @Override
    protected PreparedStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM ORDEN_DE_VENTA WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM ORDEN_DE_VENTA WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement getAllCommand(Connection conn) throws SQLException {
        String sql = "SELECT * FROM ORDEN_DE_VENTA";
        PreparedStatement ps = conn.prepareStatement(sql);
        return ps;
    }

    @Override
    protected OrdenDeVenta mapModel(ResultSet rs) throws SQLException {
        OrdenDeVenta ov = new OrdenDeVenta();
        ov.setId(rs.getInt("id"));
        ov.setTotal(rs.getDouble("total"));
        ov.setClienteId(rs.getInt("cliente_usuario_id"));
        ov.setFecha(rs.getDate("fecha"));
        ov.setEstado(rs.getString("estado"));
        ov.setDetalles(new DetalleVentaDAOImpl().getAllByForeignKey(ov.getId()));
        return ov;
    }
    
}
