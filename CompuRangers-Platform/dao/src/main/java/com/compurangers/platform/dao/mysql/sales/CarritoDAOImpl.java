package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.sales.Carrito;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import com.compurangers.platform.dao.sales.ICarritoDAO;
import java.sql.*;

public class CarritoDAOImpl extends BaseDAOImpl<Carrito> implements ICarritoDAO {
    
    @Override
    protected PreparedStatement addCommand(Connection conn, Carrito modelo) throws SQLException {
        String sql = "INSERT INTO CARRITO (total, cantidad_productos, cliente_usuario_id) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setDouble(1, modelo.getTotal());
        ps.setInt(2, modelo.getCantidadProductos());
        ps.setInt(3, modelo.getUsuarioId());
        return ps;
    }

    @Override
    protected PreparedStatement updateCommand(Connection conn, Carrito modelo) throws SQLException {
        String sql = "UPDATE CARRITO SET total = ?, cantidad_productos = ?, cliente_usuario_id = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDouble(1, modelo.getTotal());
        ps.setInt(2, modelo.getCantidadProductos());
        ps.setInt(3, modelo.getUsuarioId());
        ps.setInt(4, modelo.getId());
        return ps;
    }

    @Override
    protected PreparedStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM CARRITO WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM CARRITO WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    protected PreparedStatement getAllCommand(Connection conn) throws SQLException {
        String sql = "SELECT * FROM CARRITO";
        PreparedStatement ps = conn.prepareStatement(sql);
        return ps;
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
    
}
