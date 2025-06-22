package com.compurangers.platform.dao.mysql.user;

import com.compurangers.platform.core.domain.user.Cliente;
import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.dao.user.IClienteDAO;
import com.compurangers.platform.util.DatabaseUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class ClienteDAOImpl extends UsuarioDAOImpl implements IClienteDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, Usuario modelo) throws SQLException {
        CallableStatement csUsuario = super.addCommand(conn, modelo);
        csUsuario.execute();
        int usuarioId = csUsuario.getInt(1);

        CallableStatement csCliente = conn.prepareCall("{call add_cliente(?, ?)}");
        csCliente.setInt(1, usuarioId);
        csCliente.setString(2, ((Cliente) modelo).getDireccionPreferida());
        
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
        cliente.setId(rs.getInt("id"));
        cliente.setUsername(rs.getString("username"));
        cliente.setNombreCompleto(rs.getString("nombre"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setCorreoElectronico(rs.getString("correo"));
        cliente.setDireccion(rs.getString("direccion"));
        cliente.setAdmin(rs.getBoolean("isAdmin"));
        cliente.setCreated(rs.getTimestamp("created_at"));
        cliente.setUpdated(rs.getTimestamp("updated_at"));
        
        cliente.setDireccionPreferida(rs.getString("direccion_envio"));
        
        return cliente;
    }
    
    @Override
    public int getUserByField(String field, String value) {
        return super.getUserByField(field, value);
    }
    
    @Override
    public String getPasswordHash(int userId) {
        return super.getPasswordHash(userId);
    }
    
    @Override
    public int getClientesNuevos() {
        try (
            Connection conn = DatabaseUtil.getInstance().getConnection();
            CallableStatement stmt = conn.prepareCall("{ CALL GET_CLIENTES_NUEVOS(?) }");
        ) {
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.execute();
            return stmt.getInt(1);
        } catch (SQLException e) {
            System.err.println("Error SQL en getClientesNuevos: " + e.getMessage());
            throw new RuntimeException("No se pudo obtener la cantidad de clientes nuevos.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al obtener la cantidad de clientes nuevos.", e);
        }

    }

    
}