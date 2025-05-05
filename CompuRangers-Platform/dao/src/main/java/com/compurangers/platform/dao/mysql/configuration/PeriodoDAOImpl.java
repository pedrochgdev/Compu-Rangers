package com.compurangers.platform.dao.mysql.configuration;

import com.compurangers.platform.core.domain.configuration.Periodo;
import com.compurangers.platform.dao.configuration.IPeriodoDAO;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PeriodoDAOImpl extends BaseDAOImpl<Periodo> implements IPeriodoDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, Periodo modelo) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL add_periodo(?, ?, ?)}");
        cs.registerOutParameter(1, java.sql.Types.INTEGER); // id generado
        cs.setDate(2, new java.sql.Date(modelo.getFechaInicio().getTime()));
        cs.setDate(3, new java.sql.Date(modelo.getFechaFin().getTime()));
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, Periodo modelo) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL update_periodo(?, ?, ?)}");
        cs.setInt(1, modelo.getId());
        cs.setDate(2, new java.sql.Date(modelo.getFechaInicio().getTime()));
        cs.setDate(3, new java.sql.Date(modelo.getFechaFin().getTime()));
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL delete_periodo(?)}");
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL search_periodo(?)}");
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{CALL get_all_periodo()}");
    }

    @Override
    protected Periodo mapModel(ResultSet rs) throws SQLException {
        Periodo p = new Periodo();
        p.setId(rs.getInt("id"));
        p.setFechaInicio(rs.getDate("fecha_inicio"));
        p.setFechaFin(rs.getDate("fecha_fin"));
        return p;
    }
    
}
