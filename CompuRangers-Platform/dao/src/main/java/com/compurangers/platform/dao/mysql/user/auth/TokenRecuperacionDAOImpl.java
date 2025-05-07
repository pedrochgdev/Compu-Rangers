package com.compurangers.platform.dao.mysql.user.auth;

import com.compurangers.platform.core.domain.user.auth.TokenRecuperacion;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import com.compurangers.platform.dao.user.auth.ITokenRecuperacionDAO;
import com.compurangers.platform.util.DatabaseUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class TokenRecuperacionDAOImpl extends BaseDAOImpl<TokenRecuperacion> implements ITokenRecuperacionDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, TokenRecuperacion modelo) throws SQLException {
        String sql = "{call add_token_recuperacion(?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setInt(2, modelo.getUserId());
        cs.setString(3, modelo.getToken());
        cs.setTimestamp(4, java.sql.Timestamp.valueOf(modelo.getFechaExpiracion()));
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, TokenRecuperacion modelo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected TokenRecuperacion mapModel(ResultSet rs) throws SQLException {
        TokenRecuperacion tokenRecuperacion = new TokenRecuperacion();
        tokenRecuperacion.setId(rs.getInt("id"));
        tokenRecuperacion.setUserId(rs.getInt("usuario_id"));
        tokenRecuperacion.setToken(rs.getString("token"));
        tokenRecuperacion.setFechaExpiracion(rs.getTimestamp("fecha_expiracion").toLocalDateTime());
        tokenRecuperacion.setUsado(rs.getBoolean("usado"));
        return tokenRecuperacion;
    }


    @Override
    public TokenRecuperacion searchByToken(String token) {
        String sql = "{call search_by_token(?)}";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql);
        ) {

            cs.setString(1, token);

            ResultSet rs = cs.executeQuery();
            
            if (!rs.next()) {
                System.err.println("No se encontro el registro con token: " + token);
                return null;
            }
            
            return this.mapModel(rs);

        } catch (SQLException e) {
            System.err.println("Error SQL al obtener token" + token + ": " + e.getMessage());
            throw new RuntimeException("No se pudo encontrar el token.", e);
        } catch (Exception e) {
            System.err.println("Error inesperado" + e.getMessage());
            throw new RuntimeException("Error inesperado al obtener el token.", e);
        }
    }

    @Override
    public void markTokenAsUsed(int id) {
        String sql = "CALL mark_token_as_used(?)";
        try (Connection connection = DatabaseUtil.getInstance().getConnection();
                CallableStatement cs = connection.prepareCall(sql);
        ) {
                cs.setInt(1, id);
                cs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error SQL al obtener id" + id + ": " + e.getMessage());
            throw new RuntimeException("No se pudo encontrar el token.", e);
        } catch (Exception e) {
            System.err.println("Error inesperado" + e.getMessage());
            throw new RuntimeException("Error inesperado al actualizar token.", e);
        }
    }

}
