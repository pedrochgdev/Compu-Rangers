package com.compurangers.platform.dao.mysql.inventory;

import com.compurangers.platform.core.domain.inventory.Proveedor;
import com.compurangers.platform.dao.inventory.IProveedorDAO;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class ProveedorDAOImpl extends BaseDAOImpl<Proveedor> implements IProveedorDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, Proveedor modelo) throws SQLException {
        String sql = "{call add_proveedor(?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setString(2, modelo.getRazonSocial());
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, Proveedor modelo) throws SQLException {
        String sql = "{call update_proveedor(?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, modelo.getId());
        cs.setString(2, modelo.getRazonSocial());
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "{call delete_proveedor(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "{call search_proveedor(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{call get_all_proveedor()}");
    }

    @Override
    protected Proveedor mapModel(ResultSet rs) throws SQLException {
        Proveedor prov = new Proveedor();
        prov.setId(rs.getInt("id"));
        prov.setRazonSocial(rs.getString("razon_social"));
        return prov;
    }
    
}