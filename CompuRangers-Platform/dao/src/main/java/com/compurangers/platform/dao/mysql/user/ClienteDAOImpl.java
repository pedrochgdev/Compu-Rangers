package com.compurangers.platform.dao.mysql.user;

import com.compurangers.platform.core.domain.user.Cliente;
import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.dao.user.IClienteDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAOImpl extends UsuarioDAOImpl implements IClienteDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, Usuario modelo) throws SQLException {
        // 1. Insertar en USUARIO usando el DAO padre
        CallableStatement csUsuario = super.addCommand(conn, modelo);
        csUsuario.execute();
        int usuarioId = csUsuario.getInt(1); // Obtener ID generado
        
        // 2. Insertar en CLIENTE usando el stored procedure
        CallableStatement csCliente = conn.prepareCall("{call add_cliente(?, ?)}");
        csCliente.setInt(1, usuarioId);
        csCliente.setString(2, ((Cliente) modelo).getDireccionPreferida());
        csCliente.execute();
        
        return csCliente;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, Usuario modelo) throws SQLException {
        CallableStatement cs = conn.prepareCall("{call update_cliente(?, ?)}");
        cs.setInt(1, modelo.getId());
        cs.setString(2, ((Cliente) modelo).getDireccionPreferida());
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        // ON DELETE CASCADE eliminará automáticamente el CLIENTE
        return super.deleteCommand(conn, id); 
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        CallableStatement cs = conn.prepareCall("{call search_cliente(?)}");
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{call get_all_clientes()}");
    }

    @Override
    protected Cliente mapModel(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        // Campos de USUARIO
        cliente.setId(rs.getInt("id"));
        cliente.setUsername(rs.getString("username"));
        cliente.setNombreCompleto(rs.getString("nombre"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setCorreoElectronico(rs.getString("correo"));
        cliente.setDireccion(rs.getString("direccion"));
        cliente.setContrasena(rs.getString("password"));
        cliente.setCreated(rs.getTimestamp("created_at"));
        cliente.setUpdated(rs.getTimestamp("updated_at"));
        
        // Campos específicos de CLIENTE
        cliente.setDireccionPreferida(rs.getString("direccion_envio"));
        
        return cliente;
    }
}