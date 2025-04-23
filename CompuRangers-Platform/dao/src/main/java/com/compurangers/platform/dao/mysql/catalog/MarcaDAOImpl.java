package com.compurangers.platform.dao.mysql.catalog;

import com.compurangers.platform.core.domain.catalog.Marca;
import com.compurangers.platform.dao.catalog.IMarcaDAO;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MarcaDAOImpl extends BaseDAOImpl<Marca> implements IMarcaDAO {

    @Override
    protected PreparedStatement addCommand(Connection conn, Marca modelo) throws SQLException {
        String sql = "INSERT INTO MARCA (nombre) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, modelo.getNombre());
        return ps;
    }

    @Override
    protected PreparedStatement updateCommand(Connection conn, Marca modelo) throws SQLException {
        String sql = "UPDATE MARCA SET nombre = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, modelo.getNombre());
        ps.setInt(2, modelo.getId());
        return ps;
    }

    @Override
    protected PreparedStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM MARCA WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM MARCA WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement getAllCommand(Connection conn) throws SQLException {
        String sql = "SELECT * FROM MARCA";
        PreparedStatement ps = conn.prepareStatement(sql);
        return ps;
    }

    @Override
    protected Marca mapModel(ResultSet rs) throws SQLException {
        Marca marca = new Marca();
        marca.setId(rs.getInt("id"));
        marca.setNombre(rs.getString("nombre"));
        return marca;
    }
}
