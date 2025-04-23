package com.compurangers.platform.dao.mysql.inventory;

import com.compurangers.platform.core.domain.inventory.Proveedor;
import com.compurangers.platform.dao.inventory.IProveedorDAO;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProveedorDAOImpl extends BaseDAOImpl<Proveedor> implements IProveedorDAO{

    @Override
    protected PreparedStatement addCommand(Connection conn, Proveedor modelo) throws SQLException {
        String sql = "INSERT INTO PROVEEDOR (razon_social) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, modelo.getRazonSocial());
        return ps;
    }

    @Override
    protected PreparedStatement updateCommand(Connection conn, Proveedor modelo) throws SQLException {
        String sql = "UPDATE PROVEEDOR SET razon_social=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, modelo.getRazonSocial());
        ps.setInt(2, modelo.getId());
        return ps;
    }

    @Override
    protected PreparedStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM PROVEEDOR WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;    
    }

    @Override
    protected PreparedStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM PROVEEDOR WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement getAllCommand(Connection conn) throws SQLException {
        String sql = "SELECT * FROM CATEGORIA";
        PreparedStatement ps = conn.prepareStatement(sql);
        return ps;
    }

    @Override
    protected Proveedor mapModel(ResultSet rs) throws SQLException {
        Proveedor prov = new Proveedor();
        prov.setId(rs.getInt("id"));
        prov.setRazonSocial(rs.getString("razon_social"));
        return prov;
    }

}
