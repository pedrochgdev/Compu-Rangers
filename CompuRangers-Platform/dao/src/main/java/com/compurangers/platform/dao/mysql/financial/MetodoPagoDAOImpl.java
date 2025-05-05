package com.compurangers.platform.dao.mysql.financial;

import com.compurangers.platform.core.domain.financial.MetodoPago;
import com.compurangers.platform.dao.financial.IMetodoPagoDAO;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MetodoPagoDAOImpl extends BaseDAOImpl<MetodoPago> implements IMetodoPagoDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, MetodoPago modelo) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL add_metodo_pago(?, ?, ?, ?)}");
        cs.registerOutParameter(1, java.sql.Types.INTEGER); // OUT generated_id
        cs.setString(2, modelo.getNombre());
        cs.setString(3, modelo.getDescripcion());
        cs.setString(4, modelo.getEstado());
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, MetodoPago modelo) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL update_metodo_pago(?, ?, ?, ?)}");
        cs.setInt(1, modelo.getId());
        cs.setString(2, modelo.getNombre());
        cs.setString(3, modelo.getDescripcion());
        cs.setString(4, modelo.getEstado());
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL delete_metodo_pago(?)}");
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL search_metodo_pago(?)}");
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{CALL get_all_metodo_pago()}");
    }

    @Override
    protected MetodoPago mapModel(ResultSet rs) throws SQLException {
        MetodoPago mp = new MetodoPago();
        mp.setId(rs.getInt("id"));
        mp.setNombre(rs.getString("nombre"));
        mp.setDescripcion(rs.getString("descripcion"));
        mp.setEstado(rs.getString("estado")); // ACTIVO o INACTIVO
        return mp;
    }
    
}
