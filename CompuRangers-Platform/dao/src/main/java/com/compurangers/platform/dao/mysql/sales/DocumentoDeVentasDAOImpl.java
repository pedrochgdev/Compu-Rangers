package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.sales.DocumentoDeVentas;
import com.compurangers.platform.dao.mysql.BaseDetalleDAOImpl;
import com.compurangers.platform.dao.sales.IDocumentoDeVentasDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class DocumentoDeVentasDAOImpl extends BaseDetalleDAOImpl<DocumentoDeVentas> implements IDocumentoDeVentasDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, DocumentoDeVentas modelo) throws SQLException {
        String sql = "{call add_documento_de_ventas(?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.registerOutParameter(2, Types.INTEGER);
        cs.setDouble(3, modelo.getSubtotal());
        cs.setDouble(4, modelo.getImpuestos());
        cs.setDouble(5, modelo.getTotal());
        cs.setInt(6, modelo.getOrdenDeVentaId());
        
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, DocumentoDeVentas modelo) throws SQLException {
        String sql = "{call update_documento_de_ventas(?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        
        cs.setInt(1, modelo.getNumero());
        cs.setDouble(2, modelo.getSubtotal());
        cs.setDouble(3, modelo.getImpuestos());
        cs.setDouble(4, modelo.getTotal());
        cs.setInt(5, modelo.getOrdenDeVentaId());
        
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "{call delete_documento_de_ventas(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "{call search_documento_de_ventas(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{call get_all_documento_de_ventas()}");
    }

    @Override
    protected DocumentoDeVentas mapModel(ResultSet rs) throws SQLException {
        DocumentoDeVentas doc = new DocumentoDeVentas();
        doc.setId(rs.getInt("id"));
        doc.setNumero(rs.getInt("numero"));
        doc.setSubtotal(rs.getDouble("subtotal"));
        doc.setImpuestos(rs.getDouble("impuestos"));
        doc.setTotal(rs.getDouble("total"));
        doc.setOrdenDeVentaId(rs.getInt("orden_de_venta_id"));
        return doc;
    }

    @Override
    protected CallableStatement getAllFromFkCommand(Connection conn, int foreignKey) throws SQLException {
        String sql = "{call get_documentos_by_orden_venta(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, foreignKey);
        return cs;
    }

    @Override
    protected CallableStatement getByFkCommand(Connection conn, int foreignKey) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}