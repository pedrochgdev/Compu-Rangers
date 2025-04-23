package com.compurangers.platform.dao.mysql.catalog;

import com.compurangers.platform.core.domain.catalog.Categoria;
import com.compurangers.platform.dao.catalog.ICategoriaDAO;
import com.compurangers.platform.util.DatabaseUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements ICategoriaDAO {

    @Override
    public int add(Categoria modelo) {
        String sql = "INSERT INTO CATEGORIA (nombre, descripcion) VALUES (?, ?)";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, modelo.getNombre());
            cs.setString(2, modelo.getDescripcion());
            return cs.executeUpdate(); // Retorna 1 si se insertó correctamente
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo agregar la categoría");
        }
    }

    @Override
    public boolean update(Categoria modelo) {
        String sql = "UPDATE CATEGORIA SET nombre = ?, descripcion = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, modelo.getNombre());
            cs.setString(2, modelo.getDescripcion());
            cs.setInt(3, modelo.getId());
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
                    categoria.setDescripcion(rs.getString("descripcion"));
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
                categoria.setDescripcion(rs.getString("descripcion"));
                categorias.add(categoria);
            }
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo listar las categorias");
        }
        return categorias;
    }

}
