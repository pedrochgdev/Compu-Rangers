package com.compurangers.platform.dao.mysql.catalog;

import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.dao.catalog.IProductoDAO;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class ProductoDAOImpl extends BaseDAOImpl<Producto> implements IProductoDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, Producto modelo) throws SQLException {
        String sql = "{CALL add_producto(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.registerOutParameter(1, Types.INTEGER);
        cmd.setString(2, modelo.getSku());
        cmd.setString(3, modelo.getNombre());
        cmd.setString(4, modelo.getDescripcion());
        cmd.setDouble(5, modelo.getPrecioVenta());
        cmd.setInt(6, modelo.getCategoria().getId());
        cmd.setInt(7, modelo.getMarca().getId());
        return cmd;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, Producto modelo) throws SQLException {
        String sql = "{CALL update_producto(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt(1, modelo.getId());
        cmd.setString(2, modelo.getSku());
        cmd.setString(3, modelo.getNombre());
        cmd.setString(4, modelo.getDescripcion());
        cmd.setDouble(5, modelo.getPrecioVenta());
        cmd.setInt(6, modelo.getCategoria().getId());
        cmd.setInt(7, modelo.getMarca().getId());
        return cmd;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "{CALL delete_producto(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt(1, id);
        return cmd;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "{CALL search_producto(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt(1, id);
        return cmd;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{CALL get_all_productos()}");
    }

    @Override
    protected Producto mapModel(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getInt("id"));
        p.setSku(rs.getString("sku"));
        p.setNombre(rs.getString("nombre"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setPrecioVenta(rs.getDouble("precio_venta"));

        p.setCategoria(new CategoriaDAOImpl().search(rs.getInt("cid")));
        p.setMarca(new MarcaDAOImpl().search(rs.getInt("mid")));

        return p;
    }
}