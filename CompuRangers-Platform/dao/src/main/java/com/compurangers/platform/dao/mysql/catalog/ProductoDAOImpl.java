package com.compurangers.platform.dao.mysql.catalog;

import com.compurangers.platform.core.domain.catalog.Producto;
import com.compurangers.platform.dao.catalog.IProductoDAO;
import com.compurangers.platform.dao.mysql.BaseDAOImpl;
import com.compurangers.platform.util.DatabaseUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

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
        String sql = "{CALL update_producto(?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt(1, modelo.getId());
        cmd.setString(2, modelo.getSku());
        cmd.setString(3, modelo.getNombre());
        cmd.setString(4, modelo.getDescripcion());
        cmd.setDouble(5, modelo.getPrecioVenta());
        cmd.setInt(6, modelo.getCantidadVendida());
        cmd.setInt(7, modelo.getCategoria().getId());
        cmd.setInt(8, modelo.getMarca().getId());
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
    
    protected CallableStatement getRankingCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{CALL get_ranking_productos()}");
    }

    @Override
    protected Producto mapModel(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getInt("id"));
        p.setSku(rs.getString("sku"));
        p.setNombre(rs.getString("nombre"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setPrecioVenta(rs.getDouble("precio_venta"));
        p.setCantidadVendida(rs.getInt("cantidad_ventas"));
        p.setCategoria(new CategoriaDAOImpl().search(rs.getInt("cid")));
        p.setMarca(new MarcaDAOImpl().search(rs.getInt("mid")));

        return p;
    }

    @Override
    public List<Producto> getRanking() {
        try (
            Connection conn = DatabaseUtil.getInstance().getConnection();
            CallableStatement cmd = this.getRankingCommand(conn);
        ) {
            ResultSet rs = cmd.executeQuery();
            
            List<Producto> modelos = new ArrayList<>();
            while (rs.next()) {
                modelos.add(this.mapModel(rs));
            }
            
            return modelos;
        }
        catch (SQLException e) {
            System.err.println("Error SQL durante el ranking: " + e.getMessage());
            throw new RuntimeException("No se pudo listar el ranking.", e);
        }
        catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al listar el ranking.", e);
        }
    }
    
    protected CallableStatement searchAvanzadoCommand(Connection conn) throws SQLException {
        return conn.prepareCall("{CALL search_productos_avanzado(?, ?, ?)}");
    }
    @Override
    public List<Producto> searchAvanzado(String nombre, Integer marcaId, Integer categoriaId) {

        try (
             Connection conn = DatabaseUtil.getInstance().getConnection();
            CallableStatement cmd = this.searchAvanzadoCommand(conn);
        ) {
            // si null, se manda como SQL NULL
            if (nombre != null && !nombre.isEmpty()) {
                cmd.setString(1, nombre);
            } else {
                cmd.setNull(1, Types.VARCHAR);
            }

            if (marcaId != null && marcaId > 0) {
                cmd.setInt(2, marcaId);
            } else {
                cmd.setNull(2, Types.INTEGER);
            }

            if (categoriaId != null && categoriaId > 0) {
                cmd.setInt(3, categoriaId);
            } else {
                cmd.setNull(3, Types.INTEGER);
            }

            ResultSet rs = cmd.executeQuery();
            List<Producto> productos = new ArrayList<>();
            while (rs.next()) {
                productos.add(this.mapModel(rs));
            }
            return productos;
        } catch (SQLException e) {
            System.err.println("Error en búsqueda avanzada: " + e.getMessage());
            throw new RuntimeException("No se pudo buscar productos con filtros.", e);
        }catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al listar los productos.", e);
        }
    }
}