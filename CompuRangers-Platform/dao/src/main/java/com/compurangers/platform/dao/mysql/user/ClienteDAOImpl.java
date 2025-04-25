package com.compurangers.platform.dao.mysql.user;

import com.compurangers.platform.core.domain.user.Cliente;
import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.dao.user.IClienteDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAOImpl extends UsuarioDAOImpl implements IClienteDAO{
    
    @Override
    protected PreparedStatement addCommand(Connection conn, Usuario modelo) throws SQLException {
        PreparedStatement psUsuario = super.addCommand(conn, modelo);
        psUsuario.executeUpdate();

        ResultSet generatedKeys = psUsuario.getGeneratedKeys();
        int usuarioId = 0;
        if (generatedKeys.next()) {
            usuarioId = generatedKeys.getInt(1);
        }

        String sqlCliente = "INSERT INTO CLIENTE (usuario_id, direccion) VALUES (?, ?)";
        PreparedStatement psCliente = conn.prepareStatement(sqlCliente);
        psCliente.setInt(1, usuarioId);
        psCliente.setString(2, ((Cliente) modelo).getDireccion());
        psCliente.executeUpdate();

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
