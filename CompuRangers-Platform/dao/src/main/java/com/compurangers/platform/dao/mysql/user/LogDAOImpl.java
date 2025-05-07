package com.compurangers.platform.dao.mysql.user;

import com.compurangers.platform.core.domain.user.Log;
import com.compurangers.platform.dao.mysql.BaseDetalleDAOImpl;
import com.compurangers.platform.dao.user.ILogDAO;
import java.sql.*;
import java.util.Date;

public class LogDAOImpl extends BaseDetalleDAOImpl<Log> implements ILogDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, Log modelo) throws SQLException {
        String sql = "{call add_log(?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setString(2, modelo.getAccion());
        cs.setDate(3, new java.sql.Date(modelo.getFecha().getTime()));
        cs.setInt(4, modelo.getUsuarioId());
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, Log modelo) throws SQLException {
        String sql = "{call update_log(?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, modelo.getId());
        cs.setString(2, modelo.getAccion());
        cs.setDate(3, new java.sql.Date(modelo.getFecha().getTime()));
        cs.setInt(4, modelo.getUsuarioId());
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "{call delete_log(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "{call search_log(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{call get_all_logs()}");
    }

    @Override
    protected Log mapModel(ResultSet rs) throws SQLException {
        Log log = new Log();
        log.setId(rs.getInt("id"));
        log.setAccion(rs.getString("accion"));
        
        Date fecha = rs.getDate("fecha");
        log.setFecha(fecha != null ? new Date(fecha.getTime()) : null);
        
        log.setUsuarioId(rs.getInt("usuario_id"));
        return log;
    }
    
    @Override
    protected CallableStatement getAllFromFkCommand(Connection conn, int foreignKey) throws SQLException {
        String sql = "{call get_all_logs_by_usuario(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, foreignKey);
        return cs;
    }

    @Override
    protected CallableStatement getByFkCommand(Connection conn, int foreignKey) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}