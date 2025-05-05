package com.compurangers.platform.dao.mysql.financial;

import com.compurangers.platform.core.domain.financial.Moneda;
import com.compurangers.platform.dao.financial.IMonedaDAO;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonedaDAOImpl extends BaseDAOImpl<Moneda> implements IMonedaDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, Moneda modelo) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL add_moneda(?, ?, ?)}");
        cs.registerOutParameter(1, java.sql.Types.INTEGER); // OUT generated_id
        cs.setString(2, modelo.getCodigo());
        cs.setString(3, modelo.getNombre());
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, Moneda modelo) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL update_moneda(?, ?, ?)}");
        cs.setInt(1, modelo.getId());
        cs.setString(2, modelo.getCodigo());
        cs.setString(3, modelo.getNombre());
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL delete_moneda(?)}");
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL search_moneda(?)}");
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{CALL get_all_moneda()}");
    }

    @Override
    protected Moneda mapModel(ResultSet rs) throws SQLException {
        Moneda moneda = new Moneda();
        moneda.setId(rs.getInt("id"));
        moneda.setCodigo(rs.getString("codigo"));
        moneda.setNombre(rs.getString("nombre"));
        return moneda;
    }
    
}
