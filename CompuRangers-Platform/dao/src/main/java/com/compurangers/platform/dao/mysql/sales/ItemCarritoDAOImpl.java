package com.compurangers.platform.dao.mysql.sales;

import com.compurangers.platform.core.domain.sales.ItemCarrito;
import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.dao.catalog.IProductoDAO;
import com.compurangers.platform.dao.mysql.catalog.ProductoDAOImpl;
import com.compurangers.platform.dao.sales.IItemCarritoDAO;
import com.compurangers.platform.util.DatabaseUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemCarritoDAOImpl implements IItemCarritoDAO {

    private final IProductoDAO productoDAO = new ProductoDAOImpl();

    @Override
    public int add(ItemCarrito modelo) {
        String sql = "INSERT INTO DETALLE_CARRITO (producto_id, cantidad, subtotal, carrito_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, modelo.getProducto().getId());
            ps.setInt(2, modelo.getCantidad());
            ps.setDouble(3, modelo.getSubtotal());
            ps.setInt(4, modelo.getCarrito_id());
            if (ps.executeUpdate() == 0) {
                System.err.println("El ítem no se insertó");
                return 0;
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : -1;
            }
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo agregar el ítem al carrito");
        }
    }

    @Override
    public boolean update(ItemCarrito modelo) {
        String sql = "UPDATE DETALLE_CARRITO SET producto_id = ?, cantidad = ?, carrito_id = ?, subtotal = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, modelo.getProducto().getId());
            cs.setInt(2, modelo.getCantidad());
            cs.setInt(3, modelo.getCarrito_id());
            cs.setInt(4, modelo.getProducto().getId());
            cs.setDouble(5, modelo.getSubtotal());
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo actualizar el ítem del carrito");
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM DETALLE_CARRITO WHERE id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo eliminar el ítem del carrito");
        }
    }

    @Override
    public ItemCarrito search(int id) {
        String sql = "SELECT * FROM DETALLE_CARRITO WHERE id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    ItemCarrito item = new ItemCarrito();
                    Producto producto = productoDAO.search(rs.getInt("producto_id"));
                    item.setProducto(producto);
                    item.setCantidad(rs.getInt("cantidad"));
                    item.setCarrito_id(rs.getInt("carrito_id"));
                    item.setSubtotal(rs.getDouble("subtotal"));
                    return item;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo buscar el ítem del carrito");
        }
        return null;
    }

    @Override
    public List<ItemCarrito> getAll() {
        List<ItemCarrito> items = new ArrayList<>();
        String sql = "SELECT * FROM DETALLE_CARRITO";
        try (Connection conn = DatabaseUtil.getInstance().getConnection(); 
                CallableStatement cs = conn.prepareCall(sql); ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                ItemCarrito item = new ItemCarrito();
                Producto producto = productoDAO.search(rs.getInt("producto_id"));
                item.setProducto(producto);
                item.setCantidad(rs.getInt("cantidad"));
                item.setCarrito_id(rs.getInt("carrito_id"));
                item.setSubtotal(rs.getDouble("subtotal"));
                items.add(item);
            }
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("No se pudo listar los ítems del carrito");
        }
        return items;
    }
}
