package com.compurangers.platform.dao.mysql.inventory;

import com.compurangers.platform.core.domain.inventory.DetalleLote;
import com.compurangers.platform.dao.inventory.IDetalleLoteDAO;
import com.compurangers.platform.dao.mysql.BaseDetalleDAOImpl;
import com.compurangers.platform.dao.mysql.catalog.ProductoDAOImpl;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;


public class DetalleLoteDAOImpl extends BaseDetalleDAOImpl<DetalleLote> implements IDetalleLoteDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, DetalleLote modelo) throws SQLException {
        String sql = "{call add_detalle_lote(?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setInt(2, modelo.getCantidad());
        cs.setDouble(3, modelo.getPrecioCompra());
        cs.setInt(4, modelo.getLoteId());
        cs.setInt(5, modelo.getProducto().getId());
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, DetalleLote modelo) throws SQLException {
        String sql = "{call update_detalle_lote(?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, modelo.getId());
        cs.setInt(2, modelo.getCantidad());
        cs.setDouble(3, modelo.getPrecioCompra());
        cs.setInt(4, modelo.getLoteId());
        cs.setInt(5, modelo.getProducto().getId());
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "{call delete_detalle_lote(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "{call search_detalle_lote(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        String sql = "{call get_all_detalle_lote()}";
        CallableStatement cs = conn.prepareCall(sql);
        return cs;
    }

    @Override
    protected DetalleLote mapModel(ResultSet rs) throws SQLException {
        DetalleLote dt = new DetalleLote();
        dt.setId(rs.getInt("id"));
        dt.setCantidad(rs.getInt("cantidad"));
        dt.setPrecioCompra(rs.getDouble("precio_compra"));
        dt.setLoteId(rs.getInt("lote_id"));
        int productoId = rs.getInt("producto_id");
        dt.setProducto(new ProductoDAOImpl().search(productoId));
        return dt;
    }

    @Override
    protected CallableStatement getAllFromFkCommand(Connection conn, int foreignKey) throws SQLException {
        String sql = "{call get_all_detalle_lote_from_lote(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, foreignKey);
        return cs;
    }

    @Override
    protected CallableStatement getByFkCommand(Connection conn, int foreignKey) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
