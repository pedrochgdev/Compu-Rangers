package com.compurangers.platform.dao.mysql.catalog;

import com.compurangers.platform.core.domain.catalog.Categoria;
import com.compurangers.platform.dao.catalog.ICategoriaDAO;
import com.compurangers.platform.util.DatabaseUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements ICategoriaDAO {

    @Override
    public int add(Categoria modelo) {
        String sql = "INSERT INTO CATEGORIA (nombre) VALUES (?)";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); 
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, modelo.getNombre());
            if (ps.executeUpdate() == 0) {
                System.err.println("La categoría no se insertó");
                return 0;
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : -1;
            }
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo agregar la categoría");
        }
    }

    @Override
    public boolean update(Categoria modelo) {
        String sql = "UPDATE CATEGORIA SET nombre = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, modelo.getNombre());
            cs.setInt(2, modelo.getId());
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo actualizar la categoría");
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM CATEGORIA WHERE id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo eliminar la categoría");
        }
    }

    @Override
    public Categoria search(int id) {
        String sql = "SELECT * FROM CATEGORIA WHERE id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setId(rs.getInt("id"));
                    categoria.setNombre(rs.getString("nombre"));
                    return categoria;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo buscar la categoría");
        }
        return null;
    }

    @Override
    public List<Categoria> getAll() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORIA";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); CallableStatement cs = conn.prepareCall(sql); ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categorias.add(categoria);
            }
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo listar las categorias");
        }
        return categorias;
    }

}
