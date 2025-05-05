package com.compurangers.platform.dao.mysql.financial;

import com.compurangers.platform.core.domain.financial.Impuesto;
import com.compurangers.platform.dao.financial.IImpuestoDAO;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImpuestoDAOImpl extends BaseDAOImpl<Impuesto> implements IImpuestoDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, Impuesto modelo) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL add_impuesto(?, ?, ?, ?)}");
        cs.registerOutParameter(1, java.sql.Types.INTEGER); // generated_id
        cs.setString(2, modelo.getNombre());
        cs.setString(3, modelo.getAbreviacion());
        cs.setString(4, modelo.getTipo());
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, Impuesto modelo) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL update_impuesto(?, ?, ?, ?)}");
        cs.setInt(1, modelo.getId());
        cs.setString(2, modelo.getNombre());
        cs.setString(3, modelo.getAbreviacion());
        cs.setString(4, modelo.getTipo());
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL delete_impuesto(?)}");
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL search_impuesto(?)}");
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{CALL get_all_impuesto()}");
    }

    @Override
    protected Impuesto mapModel(ResultSet rs) throws SQLException {
        Impuesto imp = new Impuesto();
        imp.setId(rs.getInt("id"));
        imp.setNombre(rs.getString("nombre"));
        imp.setAbreviacion(rs.getString("abreviacion"));
        imp.setTipo(rs.getString("tipo"));
        return imp;
    }
    
}
