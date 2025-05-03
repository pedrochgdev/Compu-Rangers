package com.compurangers.platform.dao.mysql.inventory;

import com.compurangers.platform.core.domain.inventory.Lote;
import com.compurangers.platform.dao.inventory.ILoteDAO;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class LoteDAOImpl extends BaseDAOImpl<Lote> implements ILoteDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, Lote modelo) throws SQLException {
        String sql = "{call add_lote(?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setDate(2, new Date(modelo.getFechaCreacion().getTime()));
        cs.setString(3, modelo.getEstado());
        cs.setInt(4, modelo.getDocumentoCompraId());
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, Lote modelo) throws SQLException {
        String sql = "{call update_lote(?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, modelo.getId());
        cs.setDate(2, new Date(modelo.getFechaCreacion().getTime()));
        cs.setString(3, modelo.getEstado());
        cs.setInt(4, modelo.getDocumentoCompraId());
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "{call delete_lote(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "{call search_lote(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{call get_all_lote()}");
    }

    @Override
    protected Lote mapModel(ResultSet rs) throws SQLException {
        Lote lote = new Lote();
        lote.setId(rs.getInt("id"));
        lote.setEstado(rs.getString("estado"));
        lote.setDocumentoCompraId(rs.getInt("documento_compras_numero"));
        lote.setDetalle(new DetalleLoteDAOImpl().getAllByForeignKey(lote.getId()));
        return lote;
    }
    
}
