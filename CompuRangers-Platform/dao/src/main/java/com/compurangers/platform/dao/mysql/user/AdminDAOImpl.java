package com.compurangers.platform.dao.mysql.user;

import com.compurangers.platform.core.domain.user.Admin;
import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.dao.user.IAdminDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImpl extends UsuarioDAOImpl implements IAdminDAO {
    
    @Override
    protected PreparedStatement addCommand(Connection conn, Usuario modelo) throws SQLException {
        PreparedStatement psUsuario = super.addCommand(conn, modelo);
        psUsuario.executeUpdate();

        ResultSet generatedKeys = psUsuario.getGeneratedKeys();
        int usuarioId = 0;
        if (generatedKeys.next()) {
            usuarioId = generatedKeys.getInt(1);
        }

        String sqlAdmin = "INSERT INTO ADMIN (usuario_id, fecha_ingreso) VALUES (?, ?)";
        PreparedStatement psAdmin = conn.prepareStatement(sqlAdmin);
        psAdmin.setInt(1, usuarioId);
        psAdmin.setDate(2, new Date(((Admin) modelo).getFechaIngreso().getTime()));
        psAdmin.executeUpdate();

        return psUsuario;
    }


    @Override
    protected PreparedStatement updateCommand(Connection conn, Usuario modelo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected PreparedStatement deleteCommand(Connection conn, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected PreparedStatement searchCommand(Connection conn, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected PreparedStatement getAllCommand(Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected Usuario mapModel(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
