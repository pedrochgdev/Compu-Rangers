package com.compurangers.platform.dao.mysql.catalog;

import com.compurangers.platform.core.domain.catalog.Categoria;
import com.compurangers.platform.dao.catalog.ICategoriaDAO;
import com.compurangers.platform.dao.mysql.BaseDetalleDAOImpl;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class CategoriaDAOImpl extends BaseDetalleDAOImpl<Categoria> implements ICategoriaDAO {
    
    @Override
    protected CallableStatement addCommand(Connection conn, Categoria modelo) throws SQLException {
        String sql = "{CALL add_categoria(?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.registerOutParameter(1, Types.INTEGER);
        cmd.setString(2, modelo.getNombre());
        if (modelo.getCategoriaPadre() != null) {
            cmd.setInt(3, modelo.getCategoriaPadre().getId());
        } else {
            cmd.setNull(3, Types.INTEGER);
        }
        return cmd;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, Categoria modelo) throws SQLException {
        String sql = "{CALL update_categoria(?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt(1, modelo.getId());
        cmd.setString(2, modelo.getNombre());
        if (modelo.getCategoriaPadre() != null) {
            cmd.setInt(3, modelo.getCategoriaPadre().getId());
        } else {
            cmd.setNull(3, Types.INTEGER);
        }
        return cmd;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "{CALL delete_categoria(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt(1, id);
        return cmd;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "{CALL search_categoria(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt(1, id);
        return cmd;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        String sql = "{CALL get_all_categorias()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }

    @Override
    protected CallableStatement getAllFromFkCommand(Connection conn, int foreignKey) throws SQLException {
        String sql = "{CALL get_all_categorias_from_padre(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt(1, foreignKey);
        return cmd;
    }

    @Override
    protected Categoria mapModel(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(rs.getInt("id"));
        categoria.setNombre(rs.getString("nombre"));
        categoria.setCategoriaPadre(rs.getObject("padre_id") != null ? search(rs.getInt("padre_id")) : null);
        return categoria;
    }

    @Override
    public List<Categoria> getCategoriaSons(int categoriaId) {
        return getAllByForeignKey(categoriaId);
    }
    /*
    *Mandar a Service, tiene mas sentido en ese modulo
    */
//    @Override
//    public Categoria getCategoriaWithParents(int categoriaId) {
//        Categoria categoria = this.search(categoriaId);
//
//        if (categoria != null && categoria.getCategoriaPadre() != null) {
//            categoria.setCategoriaPadre(getCategoriaWithParents(categoria.getCategoriaPadre().getId()));
//        }
//
//        return categoria;
//    }

    @Override
    protected CallableStatement getByFkCommand(Connection conn, int foreignKey) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
