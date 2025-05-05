package com.compurangers.platform.dao.mysql.configuration;

import com.compurangers.platform.core.domain.configuration.ImpuestoPeriodo;
import com.compurangers.platform.dao.configuration.IImpuestoPeriodoDAO;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class ImpuestoPeriodoDAOImpl extends BaseDAOImpl<ImpuestoPeriodo> implements IImpuestoPeriodoDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, ImpuestoPeriodo modelo) throws SQLException {
        String sql = "{CALL add_impuesto_periodo(?, ?, ?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.registerOutParameter(1, Types.INTEGER);
        cmd.setInt(2, modelo.getPeriodoId());
        cmd.setInt(3, modelo.getImpuestoId());
        cmd.setDouble(4, modelo.getTasa());
        cmd.setString(5, modelo.getEstado());
        return cmd;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, ImpuestoPeriodo modelo) throws SQLException {
        String sql = "{CALL update_impuesto_periodo(?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt(1, modelo.getId());
        cmd.setDouble(2, modelo.getTasa());
        cmd.setString(3, modelo.getEstado());
        return cmd;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "{CALL delete_impuesto_periodo(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt(1, id);
        return cmd;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "{CALL search_impuesto_periodo(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt(1, id);
        return cmd;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        String sql = "{CALL get_all_impuesto_periodo()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }

    @Override
    protected ImpuestoPeriodo mapModel(ResultSet rs) throws SQLException {
        ImpuestoPeriodo modelo = new ImpuestoPeriodo();
        modelo.setId(rs.getInt("id"));
        modelo.setPeriodoId(rs.getInt("periodo_id"));
        modelo.setImpuestoId(rs.getInt("impuesto_id"));
        modelo.setTasa(rs.getDouble("tasa"));
        modelo.setEstado(rs.getString("estado"));
        return modelo;
    }
}