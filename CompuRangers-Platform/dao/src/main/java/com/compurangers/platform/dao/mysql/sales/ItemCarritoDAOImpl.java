package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.sales.ItemCarrito;
import com.compurangers.platform.dao.mysql.BaseDetalleDAOImpl;
import com.compurangers.platform.dao.mysql.catalog.ProductoDAOImpl;
import com.compurangers.platform.dao.sales.IItemCarritoDAO;
import com.compurangers.platform.util.DatabaseUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class ItemCarritoDAOImpl extends BaseDetalleDAOImpl<ItemCarrito> implements IItemCarritoDAO {

    @Override
    protected CallableStatement addCommand(Connection conn, ItemCarrito modelo) throws SQLException {
        String sql = "{call add_item_carrito(?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.setInt(2, modelo.getProducto().getId());
        cs.setInt(3, modelo.getCantidad());
        cs.setDouble(4, modelo.getSubtotal());
        cs.setInt(5, modelo.getCarritoId());
        return cs;
    }

    @Override
    protected CallableStatement updateCommand(Connection conn, ItemCarrito modelo) throws SQLException {
        String sql = "{call update_item_carrito(?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, modelo.getId());
        cs.setInt(2, modelo.getProducto().getId());
        cs.setInt(3, modelo.getCantidad());
        cs.setDouble(4, modelo.getSubtotal());
        cs.setInt(5, modelo.getCarritoId());
        return cs;
    }

    @Override
    protected CallableStatement deleteCommand(Connection conn, int id) throws SQLException {
        String sql = "{call delete_item_carrito(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement searchCommand(Connection conn, int id) throws SQLException {
        String sql = "{call search_item_carrito(?)}";
        CallableStatement cs = conn.prepareCall(sql);        
        cs.setInt(1, id);
        return cs;
    }

    @Override
    protected CallableStatement getAllCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{call get_all_item_carrito()}");
    }

    @Override
    protected ItemCarrito mapModel(ResultSet rs) throws SQLException {
        ItemCarrito item = new ItemCarrito();
        item.setId(rs.getInt("id"));
        item.setCantidad(rs.getInt("cantidad"));
        item.setSubtotal(rs.getDouble("subtotal"));
        item.setCarritoId(rs.getInt("carrito_id"));
        item.setProducto(new ProductoDAOImpl().search(rs.getInt("producto_id")));
        return item;
    }

    @Override
    protected CallableStatement getAllFromFkCommand(Connection conn, int foreignKey) throws SQLException {
        String sql = "{call get_all_item_from_user(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, foreignKey);
        return cs;
    }

    @Override
    protected CallableStatement getByFkCommand(Connection conn, int foreignKey) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public ItemCarrito searchItem(int carritoId, int productoId) {
        try (
            Connection conn = DatabaseUtil.getInstance().getConnection();
            CallableStatement cmd = this.searchItemCommand(conn, carritoId, productoId);
        ) {
            ResultSet rs = cmd.executeQuery();
            
            if (!rs.next()) {
                System.err.println("No se encontro el registro con id xDDD: " + carritoId + " " + productoId);
                return null;
            }
            
            return this.mapModel(rs);
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante la busqueda: " + e.getMessage());
            throw new RuntimeException("No se pudo buscar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al buscar el registro.", e);
        }
    }
    
    private CallableStatement searchItemCommand(Connection conn, int cid, int pid) throws SQLException {
        String sql = "{call search_item_in_carrito(?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, cid);
        cs.setInt(2, pid);
        return cs;
    }
    
    protected CallableStatement deleteAllCommand(Connection conn, int id) throws SQLException {
        String sql = "{call delete_all_items_from_carrito(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, id);
        return cs;
    }

    @Override
    public boolean deleteAllByCarritoId(int cid) {
        try (
            Connection conn = DatabaseUtil.getInstance().getConnection();
            CallableStatement cmd = this.deleteAllCommand(conn, cid);
        ) {
            return cmd.executeUpdate() > 0;
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante la eliminacion: " + e.getMessage());
            throw new RuntimeException("No se pudo eliminar el registro.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al eliminar el registro.", e);
        }
    }
    
}