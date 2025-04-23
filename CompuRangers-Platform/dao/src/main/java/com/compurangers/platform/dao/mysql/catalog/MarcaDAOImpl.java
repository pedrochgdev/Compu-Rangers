package com.compurangers.platform.dao.mysql.catalog;

import com.compurangers.platform.core.domain.catalog.Marca;
import com.compurangers.platform.dao.catalog.IMarcaDAO;
import com.compurangers.platform.util.DatabaseUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAOImpl implements IMarcaDAO {

    @Override
    public int add(Marca modelo) {
        String sql = "INSERT INTO MARCA (nombre) VALUES (?)";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, modelo.getNombre());
            if(ps.executeUpdate() == 0){
                System.err.println("La marca no se insertó");
                return 0;
            } 
            try(ResultSet rs = ps.getGeneratedKeys()){
                return rs.next() ? rs.getInt(1) : -1;
            }
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo agregar la marca");
        }
    }
    
    @Override
    public boolean update(Marca modelo) {
        String sql = "UPDATE MARCA SET nombre = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, modelo.getNombre());
            cs.setInt(2, modelo.getId());
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo actualizar la marca");
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM MARCA WHERE id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo eliminar la marca");
        }
    }

    @Override
    public Marca search(int id) {
        String sql = "SELECT * FROM MARCA WHERE id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    Marca marca = new Marca();
                    marca.setId(rs.getInt("id"));
                    marca.setNombre(rs.getString("nombre"));
                    return marca;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo buscar la marca");
        }
        return null;
    }

    @Override
    public List<Marca> getAll() {
        List<Marca> marcas = new ArrayList<>();
        String sql = "SELECT * FROM MARCA";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); CallableStatement cs = conn.prepareCall(sql); ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                Marca marca = new Marca();
                marca.setId(rs.getInt("id"));
                marca.setNombre(rs.getString("nombre"));
                marcas.add(marca);
            }
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo listar las marcas");
        }
        return marcas;
    }
}
