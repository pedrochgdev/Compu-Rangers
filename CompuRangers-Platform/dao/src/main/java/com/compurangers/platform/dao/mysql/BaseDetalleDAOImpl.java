package com.compurangers.platform.dao.mysql;

import com.compurangers.platform.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDetalleDAOImpl<T> extends BaseDAOImpl<T> {
    protected abstract PreparedStatement getAllFromFkCommand(Connection conn, int foreignKey) throws SQLException;
    
    public List<T> getAllByForeignKey(int foreignKey) {
        try (
            Connection conn = DatabaseUtil.getInstance().getConnection();
            PreparedStatement ps = this.getAllFromFkCommand(conn, foreignKey)
        ) {
            ResultSet rs = ps.executeQuery();
            List<T> detalles = new ArrayList<>();
            while (rs.next()) {
                detalles.add(this.mapModel(rs));
            }
            return detalles;
        } catch (SQLException e) {
            System.err.println("Error SQL al obtener detalles: " + e.getMessage());
            throw new RuntimeException("No se pudo obtener los detalles.", e);
        } catch (Exception e) {
            System.err.println("Error inesperado al obtener detalles: " + e.getMessage());
            throw new RuntimeException("Error inesperado al obtener los detalles.", e);
        }
    }
    
    @Override
    protected abstract PreparedStatement addCommand(Connection conn, T modelo) throws SQLException;
    
    @Override
    protected abstract PreparedStatement updateCommand(Connection conn, T modelo) throws SQLException;
    
    @Override
    protected abstract PreparedStatement deleteCommand(Connection conn, int id) throws SQLException;
    
    @Override
    protected abstract PreparedStatement searchCommand(Connection conn, int id) throws SQLException;
    
    @Override
    protected abstract PreparedStatement getAllCommand(Connection conn) throws SQLException;
    
    @Override
    protected abstract T mapModel(ResultSet rs) throws SQLException;
    
}

