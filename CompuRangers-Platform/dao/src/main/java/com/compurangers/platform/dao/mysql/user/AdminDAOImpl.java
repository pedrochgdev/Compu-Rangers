package com.compurangers.platform.dao.mysql.user;

import com.compurangers.platform.core.domain.user.Admin;
import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.dao.user.IAdminDAO;
import com.compurangers.platform.util.DatabaseUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class AdminDAOImpl extends UsuarioDAOImpl implements IAdminDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, Usuario modelo) throws SQLException {
        CallableStatement csUsuario = super.addCommand(conn, modelo);
        csUsuario.execute();
        int usuarioId = csUsuario.getInt(1);

        CallableStatement csAdmin = conn.prepareCall("{call add_admin(?, ?)}");
        csAdmin.setInt(1, usuarioId);
        csAdmin.setDate(2, new Date(((Admin) modelo).getFechaIngreso().getTime()));
        
        return csAdmin;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, Usuario modelo) throws SQLException {
        return super.updateCommand(conn, modelo);
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        return super.deleteCommand(conn, id);
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        CallableStatement cs = conn.prepareCall("{call search_admin(?)}");
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{call get_all_admins()}");
    }

    @Override
    protected Admin mapModel(ResultSet rs) throws SQLException {
        Admin admin = new Admin();
        admin.setId(rs.getInt("id"));
        admin.setUsername(rs.getString("username"));
        admin.setNombreCompleto(rs.getString("nombre"));
        admin.setTelefono(rs.getString("telefono"));
        admin.setCorreoElectronico(rs.getString("correo"));
        admin.setDireccion(rs.getString("direccion"));
        admin.setAdmin(rs.getBoolean("isAdmin"));
        admin.setCreated(rs.getTimestamp("created_at"));
        admin.setUpdated(rs.getTimestamp("updated_at"));
        admin.setFechaIngreso(rs.getDate("fecha_ingreso"));
        
        return admin;
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
   public double getGananciaMes() {
       try (
           Connection conn = DatabaseUtil.getInstance().getConnection();
           CallableStatement stmt = conn.prepareCall("{ CALL GET_GANANCIA_MES_ACTUAL(?) }");
       ) {
           stmt.registerOutParameter(1, Types.DOUBLE);
           stmt.execute();
           return stmt.getDouble(1);
       } catch (SQLException e) {
           System.err.println("Error SQL en getGananciaMes: " + e.getMessage());
           throw new RuntimeException("No se pudo obtener la ganancia del mes.", e);
       } catch (Exception e) {
           System.err.println("Error inesperado: " + e.getMessage());
           throw new RuntimeException("Error inesperado al obtener la ganancia del mes.", e);
       }
}

    
}