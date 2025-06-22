package com.compurangers.platform.dao.mysql.configuration;

import com.compurangers.platform.core.domain.configuration.MonedaPeriodo;
import com.compurangers.platform.dao.configuration.IMonedaPeriodoDAO;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import com.compurangers.platform.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonedaPeriodoDAOImpl extends BaseDAOImpl<MonedaPeriodo> implements IMonedaPeriodoDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, MonedaPeriodo modelo) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL add_moneda_periodo(?, ?, ?, ?, ?, ?)}");
        cs.registerOutParameter(1, java.sql.Types.INTEGER);
        cs.setInt(2, modelo.getMonedaId());
        cs.setInt(3, modelo.getPeriodoId());
        cs.setString(4, modelo.getTipoCambio());
        cs.setString(5, modelo.getEstado());
        cs.setDouble(6, modelo.getValor());
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, MonedaPeriodo modelo) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL update_moneda_periodo(?, ?, ?, ?)}");
        cs.setInt(1, modelo.getId());
        cs.setString(2, modelo.getTipoCambio());
        cs.setString(3, modelo.getEstado());
        cs.setDouble(4, modelo.getValor());
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL delete_moneda_periodo(?)}");
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL search_moneda_periodo(?)}");
        cs.setInt(1, id);
        return cs;
    }
    
    protected CallableStatement search2Command(Connection conn, int id, String tipo) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL search_moneda_periodo_with_type(?, ?)}");
        cs.setInt(1, id);
        cs.setString(2, tipo);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{CALL get_all_moneda_periodo()}");
    }

    @Override
    protected MonedaPeriodo mapModel(ResultSet rs) throws SQLException {
        MonedaPeriodo m = new MonedaPeriodo();
        m.setId(rs.getInt("id"));
        m.setMonedaId(rs.getInt("moneda_id"));
        m.setPeriodoId(rs.getInt("periodo_id"));
        m.setTipoCambio(rs.getString("tipoCambio"));
        m.setEstado(rs.getString("estado"));
        m.setValor(rs.getDouble("valor"));
        return m;
    }

    @Override
    public MonedaPeriodo searchWithType(int id, String tipo) {
        try (
            Connection conn = DatabaseUtil.getInstance().getConnection();
            CallableStatement cmd = this.search2Command(conn, id, tipo);
        ) {
            ResultSet rs = cmd.executeQuery();
            
            if (!rs.next()) {
                System.err.println("No se encontro el registro con id: " + id);
                return null;
            }
            
            return this.mapModel(rs);
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante la busqueda: " + e.getMessage());
            throw new RuntimeException("No se pudo buscar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al buscar el registro.", e);
        }
    }
    
}
