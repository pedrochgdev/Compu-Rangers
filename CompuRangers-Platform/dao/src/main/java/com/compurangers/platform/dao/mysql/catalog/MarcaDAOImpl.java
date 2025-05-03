package com.compurangers.platform.dao.mysql.catalog;

import com.compurangers.platform.core.domain.catalog.Marca;
import com.compurangers.platform.dao.catalog.IMarcaDAO;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class MarcaDAOImpl extends BaseDAOImpl<Marca> implements IMarcaDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, Marca modelo) throws SQLException {
        String sql = "{CALL add_marca(?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.registerOutParameter(1, Types.INTEGER);
        cmd.setString(2, modelo.getNombre());
        return cmd;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, Marca modelo) throws SQLException {
        String sql = "{CALL update_marca(?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt(1, modelo.getId());
        cmd.setString(2, modelo.getNombre());
        return cmd;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "{CALL delete_marca(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt(1, id);
        return cmd;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "{CALL search_marca(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt(1, id);
        return cmd;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        String sql = "{CALL get_all_marcas()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }

    @Override
    protected Marca mapModel(ResultSet rs) throws SQLException {
        Marca marca = new Marca();
        marca.setId(rs.getInt("id"));
        marca.setNombre(rs.getString("nombre"));
        return marca;
    }
}