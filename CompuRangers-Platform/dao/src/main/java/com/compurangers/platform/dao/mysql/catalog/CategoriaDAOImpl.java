package com.compurangers.platform.dao.mysql.catalog;

import com.compurangers.platform.core.domain.catalog.Categoria;
import com.compurangers.platform.dao.catalog.ICategoriaDAO;
import com.compurangers.platform.dao.mysql.BaseDetalleDAOImpl;
import com.compurangers.platform.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl extends BaseDetalleDAOImpl<Categoria> implements ICategoriaDAO {
    
    @Override
    protected PreparedStatement addCommand(Connection conn, Categoria modelo) throws SQLException {
        String sql = "INSERT INTO CATEGORIA (nombre) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, modelo.getNombre());
        return ps;
    }

    @Override
    protected PreparedStatement updateCommand(Connection conn, Categoria modelo) throws SQLException {
        String sql = "UPDATE CATEGORIA SET nombre = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, modelo.getNombre());
        ps.setInt(2, modelo.getId());
        return ps;
    }

    @Override
    protected PreparedStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM CATEGORIA WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM CATEGORIA WHERE id = ?";
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
    protected Categoria mapModel(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(rs.getInt("id"));
        categoria.setNombre(rs.getString("nombre"));
        categoria.setCategoriaPadre(search(rs.getInt("padre_id")));
        return categoria;
    }
    
    @Override
    public Categoria search(int id) {
        Categoria categoria = super.search(id);

        if (categoria != null) {
            categoria.setSubcategorias(getCategoriaSons(categoria.getId()));
        }

        return categoria;
    }
    
    @Override
    public List<Categoria> getCategoriaSons(int categoriaId) {
        return getAllByForeignKey(categoriaId);
    }
    
    @Override
    public Categoria getCategoriaWithParents(int categoriaId) {
        Categoria categoria = this.search(categoriaId);

        if (categoria != null && categoria.getCategoriaPadre() != null) {
            categoria.setCategoriaPadre(getCategoriaWithParents(categoria.getCategoriaPadre().getId()));
        }

        return categoria;
    }

    @Override
    protected PreparedStatement getAllFromFkCommand(Connection conn, int foreignKey) throws SQLException {
        String sql = "SELECT id FROM CATEGORIA WHERE padre_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, foreignKey);
        return ps;
    }

}
