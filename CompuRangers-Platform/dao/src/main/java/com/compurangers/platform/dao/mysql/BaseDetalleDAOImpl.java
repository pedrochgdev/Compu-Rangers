package com.compurangers.platform.dao.mysql;

import com.compurangers.platform.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDetalleDAOImpl<T> extends BaseDAOImpl<T> {
    protected abstract CallableStatement getAllFromFkCommand(Connection conn, int foreignKey) throws SQLException;
    protected abstract CallableStatement getByFkCommand(Connection conn, int foreignKey) throws SQLException;
    
    public List<T> getAllByForeignKey(int foreignKey) {
        try (
            Connection conn = DatabaseUtil.getInstance().getConnection();
            CallableStatement cmd = this.getAllFromFkCommand(conn, foreignKey)
        ) {
            ResultSet rs = cmd.executeQuery();
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
    
    public T getByForeignKey(int foreignKey) {
        try (
            Connection conn = DatabaseUtil.getInstance().getConnection();
            CallableStatement cmd = this.getByFkCommand(conn, foreignKey)
        ) {
            ResultSet rs = cmd.executeQuery();
            if (!rs.next()) {
                System.err.println("No se encontro el registro con id: " + foreignKey);
                return null;
            }
            return this.mapModel(rs);
        } catch (SQLException e) {
            System.err.println("Error SQL al obtener detalles: " + e.getMessage());
            throw new RuntimeException("No se pudo obtener los detalles.", e);
        } catch (Exception e) {
            System.err.println("Error inesperado al obtener detalles: " + e.getMessage());
            throw new RuntimeException("Error inesperado al obtener los detalles.", e);
        }
    }
    
    @Override
    protected abstract CallableStatement addCommand(Connection conn, T modelo) throws SQLException;
    
    @Override
    protected abstract CallableStatement updateCommand(Connection conn, T modelo) throws SQLException;
    
    @Override
    protected abstract CallableStatement deleteCommand(Connection conn, int id) throws SQLException;
    
    @Override
    protected abstract CallableStatement searchCommand(Connection conn, int id) throws SQLException;
    
    @Override
    protected abstract CallableStatement getAllCommand(Connection conn) throws SQLException;
    
    @Override
    protected abstract T mapModel(ResultSet rs) throws SQLException;
    
}

