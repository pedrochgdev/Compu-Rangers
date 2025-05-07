
package com.compurangers.platform.dao.mysql.inventory;

import com.compurangers.platform.core.domain.inventory.DocumentoCompras;
import com.compurangers.platform.dao.inventory.IDocumentoComprasDAO;
import com.compurangers.platform.dao.mysql.BaseDetalleDAOImpl;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class DocumentoComprasDAOImpl extends BaseDetalleDAOImpl<DocumentoCompras> implements IDocumentoComprasDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, DocumentoCompras modelo) throws SQLException {
        String sql = "{call add_documento_compras(?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.registerOutParameter(2, Types.INTEGER);
        cs.setDouble(3, modelo.getSubtotal());
        cs.setDouble(4, modelo.getImpuestos());
        cs.setDouble(5, modelo.getTotal());
        cs.setInt(6, modelo.getProveedorId());

        return cs;
    }


    @Override
    protected CallableStatement updateCommand(Connection conn, DocumentoCompras modelo) throws SQLException {
        String sql = "{call update_documento_compras(?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, modelo.getNumero());
        cs.setDouble(2, modelo.getSubtotal());
        cs.setDouble(3, modelo.getImpuestos());
        cs.setDouble(4, modelo.getTotal());
        cs.setInt(5, modelo.getProveedorId());
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "{call delete_documento_compras(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "{call search_documento_compras(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        String sql = "{call get_all_documento_compras()}";
        CallableStatement cs = conn.prepareCall(sql);
        return cs;
    }

    @Override
    protected DocumentoCompras mapModel(ResultSet rs) throws SQLException {
        DocumentoCompras documento = new DocumentoCompras();
        documento.setId(rs.getInt("id"));
        documento.setNumero(rs.getInt("numero"));
        documento.setSubtotal(rs.getDouble("subtotal"));
        documento.setImpuestos(rs.getDouble("impuestos"));
        documento.setTotal(rs.getDouble("total"));
        documento.setProveedorId(rs.getInt("proveedor_id"));
        return documento;
    }
    
    @Override
    protected CallableStatement getAllFromFkCommand(Connection conn, int foreignKey) throws SQLException {
        String sql = "{call get_documentos_compras_by_proveedor(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, foreignKey);
        return cs;
    }

    @Override
    protected CallableStatement getByFkCommand(Connection conn, int foreignKey) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
