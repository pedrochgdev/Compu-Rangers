package com.compurangers.platform.dao.mysql;

import com.compurangers.platform.dao.ICrud;
import com.compurangers.platform.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAOImpl<T> implements ICrud<T>{
    protected abstract CallableStatement addCommand(Connection conn, T modelo) throws SQLException;
    protected abstract CallableStatement updateCommand(Connection conn, T modelo) throws SQLException;
    protected abstract CallableStatement deleteCommand(Connection conn, int id) throws SQLException;
    protected abstract CallableStatement searchCommand(Connection conn, int id) throws SQLException;
    protected abstract CallableStatement getAllCommand(Connection conn) throws SQLException;
    
    protected abstract T mapModel(ResultSet rs) throws SQLException;
    
    @Override
    public int add(T modelo) {
        try (
            Connection conn = DatabaseUtil.getInstance().getConnection();
            CallableStatement cmd = this.addCommand(conn, modelo);
        ) {
            if (cmd.executeUpdate() == 0) {
                System.err.println("El registro no se inserto.");
                return 0;
            }
            
            return cmd.getInt(1);
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante la insercion: " + e.getMessage());
            throw new RuntimeException("No se pudo insertar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al insertar el registro.", e);
        }
    }

    @Override
    public boolean update(T modelo) {
        try (
            Connection conn = DatabaseUtil.getInstance().getConnection();
            CallableStatement cmd = this.updateCommand(conn, modelo);
        ) {
            return cmd.executeUpdate() > 0;
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante la modificacion: " + e.getMessage());
            throw new RuntimeException("No se pudo modificar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al modificar el registro.", e);
        }
    }

    @Override
    public boolean delete(int id) {
        try (
            Connection conn = DatabaseUtil.getInstance().getConnection();
            CallableStatement cmd = this.deleteCommand(conn, id);
        ) {
            return cmd.executeUpdate() > 0;
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante la eliminacion: " + e.getMessage());
            throw new RuntimeException("No se pudo eliminar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al eliminar el registro.", e);
        }
    }

    @Override
    public T search(int id) {
        try (
            Connection conn = DatabaseUtil.getInstance().getConnection();
            CallableStatement cmd = this.searchCommand(conn, id);
        ) {
            ResultSet rs = cmd.executeQuery();
            
            if (!rs.next()) {
                System.err.println("No se encontro el registro con id: " + id);
                return null;
            }
            
            return this.mapModel(rs);
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante la busqueda: " + e.getMessage());
            throw new RuntimeException("No se pudo buscar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al buscar el registro.", e);
        }
    }

    @Override
    public List<T> getAll() {
        try (
            Connection conn = DatabaseUtil.getInstance().getConnection();
            CallableStatement cmd = this.getAllCommand(conn);
        ) {
            ResultSet rs = cmd.executeQuery();
            
            List<T> modelos = new ArrayList<>();
            while (rs.next()) {
                modelos.add(this.mapModel(rs));
            }
            
            return modelos;
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante el listado: " + e.getMessage());
            throw new RuntimeException("No se pudo listar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al listar los registros.", e);
        }
    }
}