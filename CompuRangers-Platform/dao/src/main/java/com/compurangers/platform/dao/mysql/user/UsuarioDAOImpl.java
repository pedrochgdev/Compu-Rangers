package com.compurangers.platform.dao.mysql.user;

import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import com.compurangers.platform.dao.user.IUsuarioDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class UsuarioDAOImpl extends BaseDAOImpl<Usuario> implements IUsuarioDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, Usuario modelo) throws SQLException {
        String sql = "{call add_usuario(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        
        // Registrar parámetro OUT
        cs.registerOutParameter(1, Types.INTEGER);
        
        // Establecer parámetros IN
        cs.setString(2, modelo.getUsername());
        cs.setString(3, modelo.getNombreCompleto());
        cs.setString(4, modelo.getTelefono());
        cs.setString(5, modelo.getCorreoElectronico());
        cs.setString(6, modelo.getDireccion());
        cs.setString(7, modelo.getContrasena());
        
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, Usuario modelo) throws SQLException {
        String sql = "{call update_usuario(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        
        cs.setInt(1, modelo.getId());
        cs.setString(2, modelo.getUsername());
        cs.setString(3, modelo.getNombreCompleto());
        cs.setString(4, modelo.getTelefono());
        cs.setString(5, modelo.getCorreoElectronico());
        cs.setString(6, modelo.getDireccion());
        cs.setString(7, modelo.getContrasena());
        
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "{call delete_usuario(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "{call search_usuario(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{call get_all_usuarios()}");
    }

    @Override
    protected Usuario mapModel(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario login(String correo, String contraseña) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean existsByEmail(String correo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
