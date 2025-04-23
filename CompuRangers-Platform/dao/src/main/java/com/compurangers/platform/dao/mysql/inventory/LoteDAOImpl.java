package com.compurangers.platform.dao.mysql.inventory;

import com.compurangers.platform.core.domain.inventory.Lote;
import com.compurangers.platform.dao.inventory.ILoteDAO;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoteDAOImpl extends BaseDAOImpl<Lote> implements ILoteDAO{

    @Override
    protected PreparedStatement addCommand(Connection conn, Lote modelo) throws SQLException {
        String sql = "INSERT INTO LOTE (fecha_creacion, estado, documento_compras_numero) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setDate(1, new Date(modelo.getFechaCreacion().getTime()));
        return ps;
    }

    @Override
    protected PreparedStatement updateCommand(Connection conn, Lote modelo) throws SQLException {
        String sql = "UPDATE LOTE SET fecha_creacion=?, estado=?, documento_compras_numero=? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDate(1, new Date(modelo.getFechaCreacion().getTime()));
        ps.setString(2, modelo.getEstado());
        ps.setInt(3, modelo.getDocumentoCompraId());
        ps.setInt(4, modelo.getId());
        return ps;   
    }

    @Override
    protected PreparedStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM LOTE WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM LOTE WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement getAllCommand(Connection conn) throws SQLException {
        String sql = "SELECT * FROM LOTE";
        PreparedStatement ps = conn.prepareStatement(sql);
        return ps;
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
