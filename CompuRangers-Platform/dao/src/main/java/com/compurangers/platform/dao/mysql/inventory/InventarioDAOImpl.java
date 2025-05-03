package com.compurangers.platform.dao.mysql.inventory;

import com.compurangers.platform.core.domain.inventory.Inventario;
import com.compurangers.platform.dao.inventory.IInventarioDAO;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import com.compurangers.platform.dao.mysql.catalog.ProductoDAOImpl;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class InventarioDAOImpl extends BaseDAOImpl<Inventario> implements IInventarioDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, Inventario modelo) throws SQLException {
        String sql = "{call add_inventario(?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setInt(2, modelo.getCantidadDisponible());
        cs.setInt(3, modelo.getLoteId());
        cs.setInt(4, modelo.getProducto().getId());
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, Inventario modelo) throws SQLException {
        String sql = "{call update_inventario(?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, modelo.getId());
        cs.setInt(2, modelo.getCantidadDisponible());
        cs.setInt(3, modelo.getLoteId());
        cs.setInt(4, modelo.getProducto().getId());
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "{call delete_inventario(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "{call search_inventario(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{call get_all_inventario()}");
    }

    @Override
    protected Inventario mapModel(ResultSet rs) throws SQLException {
        Inventario inv = new Inventario();
        inv.setId(rs.getInt("id"));
        inv.setCantidadDisponible(rs.getInt("cantidad_disponible"));
        inv.setLoteId(rs.getInt("lote_id"));
        inv.setProducto(new ProductoDAOImpl().search(rs.getInt("producto_id")));
        return inv;
    }

}
