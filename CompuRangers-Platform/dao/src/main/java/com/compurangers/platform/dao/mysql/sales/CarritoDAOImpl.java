package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.sales.Carrito;
import com.compurangers.platform.dao.mysql.BaseDetalleDAOImpl;
import com.compurangers.platform.dao.sales.ICarritoDAO;
import java.sql.*;

public class CarritoDAOImpl extends BaseDetalleDAOImpl<Carrito> implements ICarritoDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, Carrito modelo) throws SQLException {
        String sql = "{call add_carrito(?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setDouble(2, modelo.getTotal());
        cs.setInt(3, modelo.getCantidadProductos());
        cs.setInt(4, modelo.getUsuarioId());
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, Carrito modelo) throws SQLException {
        String sql = "{call update_carrito(?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, modelo.getId());
        cs.setDouble(2, modelo.getTotal());
        cs.setInt(3, modelo.getCantidadProductos());
        cs.setInt(4, modelo.getUsuarioId());
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "{call delete_carrito(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "{call search_carrito(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{call get_all_carrito()}");
    }

    @Override
    protected Carrito mapModel(ResultSet rs) throws SQLException {
        Carrito carrito = new Carrito();
        carrito.setId(rs.getInt("id"));
        carrito.setTotal(rs.getDouble("total"));
        carrito.setCantidadProductos(rs.getInt("cantidad_productos"));
        carrito.setUsuarioId(rs.getInt("cliente_usuario_id"));
        carrito.setItems(new ItemCarritoDAOImpl().getAllByForeignKey(carrito.getId()));
        return carrito;
    }

    @Override
    protected CallableStatement getAllFromFkCommand(Connection conn, int foreignKey) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected CallableStatement getByFkCommand(Connection conn, int foreignKey) throws SQLException {
        String sql = "{call get_carrito_by_user(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, foreignKey);
        return cs;
    }
    
}