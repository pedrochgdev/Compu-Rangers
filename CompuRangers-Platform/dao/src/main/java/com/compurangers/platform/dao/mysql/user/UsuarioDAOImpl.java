package com.compurangers.platform.dao.mysql.user;

import com.compurangers.platform.core.domain.user.Usuario;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import com.compurangers.platform.dao.user.IUsuarioDAO;
import com.compurangers.platform.util.DatabaseUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class UsuarioDAOImpl extends BaseDAOImpl<Usuario> implements IUsuarioDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, Usuario modelo) throws SQLException {
        String sql = "{call add_usuario(?, ?, ?, ?, ?, ?, ?, ?)}";
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
        cs.setBoolean(8, modelo.isAdmin());
        
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, Usuario modelo) throws SQLException {
        String sql = "{call update_usuario(?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        
        cs.setInt(1, modelo.getId());
        cs.setString(2, modelo.getUsername());
        cs.setString(3, modelo.getNombreCompleto());
        cs.setString(4, modelo.getTelefono());
        cs.setString(5, modelo.getCorreoElectronico());
        cs.setString(6, modelo.getDireccion());
        cs.setString(7, modelo.getContrasena());
        cs.setBoolean(8, modelo.isAdmin());
        
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
    public int getUserByField(String field, String value) {
        String sql = "";
        if ("email".equalsIgnoreCase(field)) {
            sql = "{call get_user_id_by_email(?, ?)}";
        } else if ("username".equalsIgnoreCase(field)) {
            sql = "{call get_user_id_by_username(?, ?)}";
        } else {
            throw new IllegalArgumentException("Campo no soportado: " + field);
        }

        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setString(1, value);
            cs.registerOutParameter(2, Types.INTEGER);

            cs.execute();
            return cs.getInt(2);

        } catch (SQLException e) {
            System.err.println("Error SQL al obtener id" + field + ": " + e.getMessage());
            throw new RuntimeException("No se pudo encontrar el ID del usuario.", e);
        } catch (Exception e) {
            System.err.println("Error inesperado" + e.getMessage());
            throw new RuntimeException("Error inesperado al obtener el id.", e);
        }
    }

    @Override
    public String getPasswordHash(int userId) {
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall("{call get_user_password_by_id(?, ?)}")) {

            cs.setInt(1, userId);
            cs.registerOutParameter(2, Types.VARCHAR);

            cs.execute();

            return cs.getString(2);

        } catch (SQLException e) {
            System.err.println("Error SQL al obtener: " + e.getMessage());
            throw new RuntimeException("No se pudo encontrar la contraseña.", e);
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al obtener el hash de la contraseña.", e);
        }
    }

    @Override
    public boolean updatePassword(int userId, String hashedPassword) {
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
         CallableStatement cs = conn.prepareCall("{CALL update_user_password(?, ?)}")) {

            cs.setInt(1, userId);
            cs.setString(2, hashedPassword);
            return cs.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error SQL al actualizar: " + e.getMessage());
            throw new RuntimeException("No se pudo actualizar la contraseña.", e);
        }catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al obtener el actualizar la contraseña.", e);
        }
    }

}
